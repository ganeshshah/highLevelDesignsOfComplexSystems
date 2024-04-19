package interviewpreparation.largefiletransfer.self;

import java.io.*;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.DeflaterOutputStream;

public class Sender {
    private static final int CHUNK_SIZE = 1024 * 1024 * 4;  // 4 MB
    private static final String SERVER_HOST = "localhost";
    private static final int PORT = 8080;
    private static final int TOTAL_THREADS = 4;
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        // file to be sent

        File file = new File("c://SendData/some_large_file.txt");

        long totalFileSizeInBytes = file.length();

        int totalFileCHunks = (int) (totalFileSizeInBytes / CHUNK_SIZE);

        ExecutorService executor = Executors.newFixedThreadPool(TOTAL_THREADS);

        // create a latch to wait until all threads completes sending the chunks
        CountDownLatch latch = new CountDownLatch(totalFileCHunks);
        // send all chunks asynchronously
        try (FileInputStream fis = new FileInputStream(file)) {
            for (int i = 0; i < totalFileCHunks; i++) {
                int chunkId = i;
                SendingTask task = new SendingTask(fis, chunkId, latch);
                executor.submit(task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        latch.await();

    }

    static class SendingTask implements Runnable {

        private FileInputStream fis;
        private int chunkId;
        private CountDownLatch latch;

        SendingTask(FileInputStream fis, int chunkId, CountDownLatch latch) {
            this.fis = fis;
            this.chunkId = chunkId;
            this.latch = latch;
        }

        @Override
        public void run() {
            {
                int retries = 0;
                boolean success = false;


                while (!success && retries < MAX_RETRIES) {
                    try {
                        byte[] chunk = new byte[CHUNK_SIZE];
                        int bytesRead = fis.read(chunk);
                        if (bytesRead > 0) {

                            // Compress the chunk data
                            ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();
                            try (DeflaterOutputStream deflaterStream = new DeflaterOutputStream(compressedStream)) {
                                deflaterStream.write(chunk, 0, bytesRead);
                            }
                            byte[] compressedData = compressedStream.toByteArray();

                            // Calculate the checksum of the compressed data
                            MessageDigest digest = MessageDigest.getInstance("SHA-256");
                            byte[] checksum = digest.digest(compressedData);

                            // send data with checksum
                            try (Socket socket = new Socket(SERVER_HOST, PORT);
                                 OutputStream outputStream = socket.getOutputStream()) {
                                // Send the checksum
                                outputStream.write(checksum);
                                // Send the compressed chunk data
                                outputStream.write(compressedData);
                                success = true;
                            }
                        }
                    } catch (IOException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } finally {
                        if (!success) {
                            retries++;
                            System.out.println("Chunk transfer failed. Retrying... (Attempt " + retries + ")");
                        }
                    }
                }
                if (!success) {
                    System.out.println("Max retries exceeded for chunk " + chunkId + ". Skipping...");
                }
                latch.countDown(); // Decrease the count
            }
        }
    }

}
