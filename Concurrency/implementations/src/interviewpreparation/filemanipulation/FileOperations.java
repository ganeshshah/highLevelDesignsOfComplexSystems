package interviewpreparation.filemanipulation;

import java.io.*;

public class FileOperations {
    public static void main(String[] args) {
        String fileName = "example.txt";

        // Write data to file
        writeToFile(fileName, "Hello, world!");

        // Read data from file
        String content = readFromFile(fileName);
        System.out.println("File content: " + content);

        // Seek operation - move to a specific position in the file
        long newPosition = seekInFile(fileName, 7);
        System.out.println("New position after seeking: " + newPosition);
    }

    // Method to write data to a file
    public static void writeToFile(String fileName, String data) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(data.getBytes());
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read data from a file
    public static String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                content.append((char) byteData);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return content.toString();
    }

    // Method to perform seek operation in a file
    public static long seekInFile(String fileName, long position) {
        long newPosition = -1;
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(position); // Move to the specified position
            newPosition = raf.getFilePointer(); // Get the new position
        } catch (IOException e) {
            System.err.println("Error seeking in file: " + e.getMessage());
        }
        return newPosition;
    }
}
