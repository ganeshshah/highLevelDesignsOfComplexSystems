package largeFileProcessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ProcessLargeFile {


    public static void main(String[] args) throws IOException {

        String dummyValue = ",MESSAGE,I am a dummy text generated for multi-threading performance evaluation,some xyz text,inserting dummy values to increase file size,12345,21,27,1997,1.0000,";
        File file = new File("C:\\low-level-design\\Low-Level-Design-Java\\Concurrency\\JavaConcurrencyBookImplementation\\src\\largeFileProcessing\\fileOutput\\eventDetails.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter(file);

        for(int i=0;i<9000000;i++){
            String s = i + dummyValue + new Date() + System.lineSeparator();
            fw.write(s);
        }
    }
}
