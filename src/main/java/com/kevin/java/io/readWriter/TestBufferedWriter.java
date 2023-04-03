package com.kevin.java.io.readWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by: kevin
 * Date: 2022-11-30
 */
public class TestBufferedWriter {
    public void test(String str){
        String filenameTemp = "target/bufferWriter.txt";
        BufferedWriter osw =null;
        FileOutputStream fos =null;
        try {
            fos = new FileOutputStream(filenameTemp);
            osw = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
            osw.write(str);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(osw!=null){
                try {
                    osw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        TestBufferedWriter bufferedWriter = new TestBufferedWriter();
        bufferedWriter.test("世界");
    }
}
