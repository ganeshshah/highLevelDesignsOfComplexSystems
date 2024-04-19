package interviewpreparation.filemanipulation;

import java.io.IOException;
import java.io.RandomAccessFile;

public class BlockReadWrite {
    private RandomAccessFile file;
    private int blockSize;

    public BlockReadWrite(String filename, int blockSize) throws IOException {
        this.blockSize = blockSize;
        this.file = new RandomAccessFile(filename, "rw");
    }

    public void writeBlock(int blockNumber, byte[] data) throws IOException {
        long position = (long) blockNumber * blockSize;
        file.seek(position);
        file.write(data);
    }

    public byte[] readBlock(int blockNumber) throws IOException {
        byte[] blockData = new byte[blockSize];
        long position = (long) blockNumber * blockSize;
        file.seek(position);
        int bytesRead = file.read(blockData);
        if (bytesRead == -1) {
            return null; // End of file
        }
        return blockData;
    }

    public void close() throws IOException {
        file.close();
    }

    public static void main(String[] args) {
        try {
            BlockReadWrite blockRW = new BlockReadWrite("example.bin", 64);

            // Write data
            byte[] data1 = "This is block 1".getBytes();
            byte[] data2 = "This is block 2".getBytes();
            blockRW.writeBlock(0, data1);
            blockRW.writeBlock(1, data2);

            // Read data
            int blockNumber = 0;
            byte[] blockData;
            while ((blockData = blockRW.readBlock(blockNumber)) != null) {
                System.out.println("Block " + blockNumber + ": " + new String(blockData));
                blockNumber++;
            }

            blockRW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
