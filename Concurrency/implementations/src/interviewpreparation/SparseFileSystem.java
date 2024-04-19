package interviewpreparation;

import java.util.HashMap;
import java.util.Map;

// Represents a file in the sparse file system
class File {
    private String name;
    private int size;
    private Map<Integer, Byte> data; // Map to store non-zero data blocks

    public File(String name, int size) {
        this.name = name;
        this.size = size;
        this.data = new HashMap<>();
    }

    // Write data to the file at a specific offset
    public void write(int offset, byte[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            data.put(offset + i, buffer[i]);
        }
    }

    // Read data from the file at a specific offset
    public byte read(int offset) {
        return data.getOrDefault(offset, (byte) 0);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}

// Represents the sparse file system
public class SparseFileSystem {
    private Map<String, File> files;

    public SparseFileSystem() {
        files = new HashMap<>();
    }

    // Create a new file in the file system
    public void createFile(String name, int size) {
        File file = new File(name, size);
        files.put(name, file);
    }

    // Write data to a file in the file system
    public void writeFile(String name, int offset, byte[] buffer) {
        File file = files.get(name);
        if (file != null) {
            file.write(offset, buffer);
        }
    }

    // Read data from a file in the file system
    public byte readFile(String name, int offset) {
        File file = files.get(name);
        if (file != null) {
            return file.read(offset);
        }
        return 0; // Default value if file not found or offset out of bounds
    }

    public static void main(String[] args) {
        SparseFileSystem fileSystem = new SparseFileSystem();
        fileSystem.createFile("example.txt", 1024);

        // Writing data to file
        byte[] data = "Hello, World!".getBytes();
        fileSystem.writeFile("example.txt", 0, data);

        // Reading data from file
        for (int i = 0; i < data.length+1; i++) {

            byte b = fileSystem.readFile("example.txt", i);
            System.out.print((char) b);
        }
    }
}
