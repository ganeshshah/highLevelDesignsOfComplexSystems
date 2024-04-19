package interviewpreparation.largefiletransfer.self;


import java.io.*;
import java.net.*;
import java.security.*;
import java.util.zip.*;

public class Receiver {

    private static final int CHUNK_SIZE = 1024 * 1024; // 1MB chunk size
    private static final int MAX_RETRIES = 3; // Maximum number of retry attempts

    public static void main(String[] args) {
        int portNumber = 12345; // Specify the port number to listen on

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {

            // Accept incoming connection
            try (Socket clientSocket = serverSocket.accept();
                 InputStream inputStream = clientSocket.getInputStream()) {
                receiveFile(inputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(InputStream inputStream) throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream))) {
            int chunkNumber = 0;
            while (true) {
                // Read checksum
                byte[] checksum = new byte[32];
                int checksumBytesRead = dataInputStream.read(checksum);
                if (checksumBytesRead == -1) {
                    break; // End of stream
                }

                // Read compressed chunk data
                byte[] compressedData = new byte[CHUNK_SIZE];
                int compressedBytesRead = dataInputStream.read(compressedData);
                if (compressedBytesRead == -1) {
                    break; // End of stream
                }

                // Verify checksum
                MessageDigest digest;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    throw new IOException("Error initializing SHA-256 MessageDigest", e);
                }
                byte[] calculatedChecksum = digest.digest(compressedData);
                if (!MessageDigest.isEqual(checksum, calculatedChecksum)) {
                    throw new IOException("Checksum verification failed for chunk " + chunkNumber);
                }

                // Decompress the chunk data
                byte[] chunkData;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     InflaterOutputStream inflaterStream = new InflaterOutputStream(baos)) {
                    inflaterStream.write(compressedData, 0, compressedBytesRead);
                    inflaterStream.finish();
                    chunkData = baos.toByteArray();
                }

                // Write the chunk data to file (you can customize this part)
                writeFileChunk(chunkData);

                chunkNumber++;
            }
        }
    }

    private static void writeFileChunk(byte[] data) {
        // This method should be implemented based on your requirement.
        // You can write the received chunk data to a file, store it in memory, or process it in any other way.
        // For simplicity, let's just print the chunk size here.
        System.out.println("Received chunk of size: " + data.length);
    }
}
