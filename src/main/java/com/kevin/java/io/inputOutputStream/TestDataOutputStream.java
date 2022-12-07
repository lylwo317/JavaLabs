package com.kevin.java.io.inputOutputStream;

import java.io.*;

/**
 * Created by: kevin
 * Date: 2022-11-29
 */
public class TestDataOutputStream {
    public static void main(String[] args) {
        // Data Stream写到输入流中
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(
                    "target/datasteam.txt"));
//            dos.writeBytes("𠮷");
            dos.writeBytes("世界"); // Java UTF-16 编码(纯英文是Latin1编码)。char(16bit) -> byte(8bit)，写入的低位
//            dos.writeChars("𠮷"); // 按照UTF-16写入
            dos.writeChars("世界"); // 按照UTF-16写入。1 char(16bit) -> 2 byte(8bit)
            // 按照UTF-8写入(UTF8变长，开头2字节是由writeUTF函数写入的长度信息，方便readUTF函数读取)
            dos.writeUTF("世界");// UTF-16 -> UTF-8。写入UTF-8
            dos.flush();
            dos.close();

            // Data Stream 读取
            DataInputStream dis = new DataInputStream(new FileInputStream(
                    "target/datasteam.txt"));
            // 读取字节
            byte[] b = new byte[2];
            dis.read(b);
            System.out.println(new String(b, 0, 2));

            // 读取字符
            char[] c = new char[2];
            for (int i = 0; i < 2; i++) {
                c[i] = dis.readChar();
            }
            System.out.println(new String(c, 0, 2));

            // 读取UTF
            System.out.println(dis.readUTF());

            dis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
