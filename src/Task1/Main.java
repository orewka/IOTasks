package Task1;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("src/Task1/1.txt","r");
        FileOutputStream fileOutputStream1 = new FileOutputStream("src/Task1/2.txt");
        FileOutputStream fileOutputStream2 = new FileOutputStream("src/Task1/3.txt")) {
            raf.seek(0);
            String text = raf.readLine();
            fileOutputStream1.write(text.getBytes(), 0, text.length() / 2);
            raf.seek(20);
            text = raf.readLine();
            fileOutputStream2.write(text.getBytes(), 0, text.length());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream1 = new FileInputStream("src/Task1/2.txt");
        FileInputStream fileInputStream2 = new FileInputStream("src/Task1/3.txt");
        FileOutputStream fileOutputStream3 = new FileOutputStream("src/Task1/4.txt");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            SequenceInputStream sequenceInputStream = new SequenceInputStream(fileInputStream1, fileInputStream2);) {
            byte[] tmp = new byte[10];
            int data;
            while ((data = sequenceInputStream.read(tmp)) > 0) {
                byteArrayOutputStream.write(tmp, 0, data);
            }
            String text2 = new String(byteArrayOutputStream.toByteArray());
            fileOutputStream3.write(text2.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
