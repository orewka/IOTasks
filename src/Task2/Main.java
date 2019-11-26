package Task2;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (FileOutputStream file = new FileOutputStream("src/Task2/1.txt");
        FileInputStream file1 = new FileInputStream("src/Task2/1.txt");) {
            XorOutputStream xorOutputStream = new XorOutputStream(file);
            XorInputStream xorInputStream = new XorInputStream(file1);
            String text = "Ура";
            xorOutputStream.write(text.getBytes());
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buf = new byte[10];
        int data;
        while ((data = xorInputStream.read(buf)) > 0) {
            bout.write(buf, 0 , data);
        }
        String string = new String(bout.toByteArray());
        System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class XorInputStream extends FilterInputStream {
    public XorInputStream(InputStream in) {
        super(in);
    }
    @Override
    public int read(byte[] b) throws IOException {
        int l = in.read(b);
        byte[] bKey = "55".getBytes();
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte) (b[i] ^ bKey[i % bKey.length]);
        }
        return l;
    }
}

class XorOutputStream extends FilterOutputStream {
    public XorOutputStream(OutputStream out) {
        super(out);
    }
    @Override
    public void write(byte[] b) throws IOException {
        byte[] bKey = "55".getBytes();
        byte[] res = new byte[b.length];
        for (int i = 0; i < b.length; i++) {
            res[i] = (byte) (b[i] ^ bKey[i % bKey.length]);
        }
        out.write(res);
    }
}
