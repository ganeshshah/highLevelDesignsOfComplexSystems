package interviewpreparation.largefiletransfer;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.concurrent.*;
import java.util.zip.*;

public class LargeFileTransfer {

    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB chunk size
    private static final int NUM_THREADS = 4; // Number of threads to use for transfer
    private static final int MAX_RETRIES = 3; // Maximum number of retry attempts

    public static void main(String[] args) {
        String sourceFilePath = "path_to_large_file";
        String destinationHost = "destination_host";
        int destinationPort = 12345; // Specify the destination port

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        try {
            File sourceFile = new File(sourceFilePath);
            long fileSize = sourceFile.length();

            // Calculate the number of chunks
            int numChunks = (int) Math.ceil((double) fileSize / CHUNK_SIZE);

            // Create a CountDownLatch to wait for all threads to finish
            CountDownLatch latch = new CountDownLatch(numChunks);

            try (FileInputStream fis = new FileInputStream(sourceFile)) {
                for (int i = 0; i < numChunks; i++) {
                    int chunkNumber = i;
                    executor.execute(() -> {
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

                                    try (Socket socket = new Socket(destinationHost, destinationPort);
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
                            System.out.println("Max retries exceeded for chunk " + chunkNumber + ". Skipping...");
                        }
                        latch.countDown(); // Decrease the count
                    });
                }
            }

            // Wait for all threads to finish
            latch.await();

            System.out.println("File transfer completed.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shutdown the executor service
        }
    }
}
