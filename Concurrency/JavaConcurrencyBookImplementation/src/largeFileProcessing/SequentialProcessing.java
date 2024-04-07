package largeFileProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SequentialProcessing {


    public static void main(String[] args) throws IOException {

        File file = new File("C:\\low-level-design\\Low-Level-Design-Java\\Concurrency\\JavaConcurrencyBookImplementation\\src\\largeFileProcessing\\fileOutput\\eventDetails.txt");


        FileReader fr = new FileReader(file);
        int count = 0;
//        while(fr.){
//            count++;
//        }
        System.out.println(count);

    }
}
