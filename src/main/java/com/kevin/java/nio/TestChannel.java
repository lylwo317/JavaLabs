package com.kevin.java.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by: kevin
 * Date: 2022-11-27
 */
public class TestChannel {

    private void readFile() throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

    public static void main(String[] args) {
        String currentPath = null;
        try {
            currentPath = new java.io.File(".").getCanonicalPath();
            System.out.println("Current dir:" + currentPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TestChannel testChannel = new TestChannel();
        try {
            testChannel.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
