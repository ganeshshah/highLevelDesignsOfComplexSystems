package interviewpreparation.filemanipulation;

import java.util.Arrays;

import java.util.Arrays;

public class StorageSystem {
    private byte[][] storage; // 2D array to store blocks
    private int currentPosition; // Current position within the storage
    private int blockSize; // Block size in bytes

    public StorageSystem(int numBlocks, int blockSize) {
        this.storage = new byte[numBlocks][blockSize];
        this.blockSize = blockSize;
        this.currentPosition = 0;
    }

    // Method to write data to the storage
    public void write(byte[] data) {
        if (currentPosition >= storage.length * blockSize) {
            System.err.println("Storage overflow: Unable to write data.");
            return;
        }

        int remainingBytes = blockSize - currentPosition % blockSize;
        int bytesToWrite = Math.min(data.length, remainingBytes);

        System.arraycopy(data, 0, storage[currentPosition / blockSize], currentPosition % blockSize, bytesToWrite);
        currentPosition += bytesToWrite;
    }

    // Method to read data from the storage
    public byte[] read(int numBytes) {
        byte[] result = new byte[numBytes];

        int bytesRead = 0;
        while (bytesRead < numBytes && currentPosition < storage.length * blockSize) {
            int remainingBytes = (storage.length * blockSize) - currentPosition;
            int bytesToRead = Math.min(numBytes - bytesRead, remainingBytes);

            System.arraycopy(storage[currentPosition / blockSize], currentPosition % blockSize, result, bytesRead, bytesToRead);
            currentPosition += bytesToRead;
            bytesRead += bytesToRead;
        }

        if (bytesRead < numBytes) {
            System.err.println("End of storage reached: Unable to read remaining bytes.");
        }

        return result;
    }

    // Method to seek to a specific position within the storage
    public void seek(int position) {
        if (position < 0 || position >= storage.length * blockSize) {
            System.err.println("Invalid seek position: " + position);
            return;
        }
        currentPosition = position;
    }

    // Method to print the contents of the storage
    public void printStorage() {
        for (byte[] block : storage) {
            System.out.println(Arrays.toString(block));
        }
    }

    public static void main(String[] args) {
        // Create a storage system with 5 blocks of 64 bytes each
        StorageSystem storageSystem = new StorageSystem(5, 64);

        // Write data to the storage
        storageSystem.write("Hello, world!".getBytes());

        // Read data from the storage
        byte[] data = storageSystem.read(13);
        System.out.println("Read data: " + new String(data));

        // Seek to a specific position
        storageSystem.seek(0);

        // Print the contents of the storage
        storageSystem.printStorage();
    }
}
