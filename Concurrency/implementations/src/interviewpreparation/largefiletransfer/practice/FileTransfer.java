//package interviewpreparation.largefiletransfer.practice;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.zip.DeflaterOutputStream;
//import java.util.zip.InflaterOutputStream;
//
//public class FileTransfer {
//
//    int chunkSize = 1024;
//    boolean success = false;
//
//    void transfer() throws IOException, NoSuchAlgorithmException {
//
//        ServerSocket serverSocket = new ServerSocket(1234);
//        Socket clientSocket = serverSocket.accept();
//        InputStream is = clientSocket.getInputStream();
//
//        String sourceFilePath = "source_path_location";
//        File file = new File(sourceFilePath);
//        FileInputStream fileInputStream = new FileInputStream(file);
//
//        byte[] chunk = new byte[chunkSize];
//        int bytesRead = fileInputStream.read(chunk);
//
//        if(bytesRead > 0){
//
//            // compress the data
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            DeflaterOutputStream daos = new DeflaterOutputStream(baos);
//            daos.write(chunk,0,bytesRead);
//            byte[] compressedData = baos.toByteArray();
//
//
//            // calculate checksum of the compressed data
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
//            byte[] checkSum = digest.digest(compressedData);
//
//            Socket socket = new Socket("host",1234);
//            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write(checkSum);
//            outputStream.write(compressedData);
//            success = true;
//
//            while(!success){
//
//
//
//
//            }
//        }
//
//    }
//
//
//    void Receiver(InputStream inputStream) throws IOException {
//
//        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
//
//        byte[] checkSum = new byte[32];
//        int byteRead = dataInputStream.read(checkSum);
//
//
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] computedCheckSum = digest.digest(data);
//
//        // use as fis
//        byte[] compressedData = new byte[10];
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        InflaterOutputStream is = new InflaterOutputStream(byteArrayOutputStream);
//        is.write(compressedData);
//        byte[] deCompressedData = byteArrayOutputStream.toByteArray();
//        is.finish();
//
//
//    }
//}
