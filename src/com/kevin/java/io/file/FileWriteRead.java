package com.kevin.java.io.file;

import java.io.*;

/**
 * Created by kevin on 7/26/16.
 */
public class FileWriteRead {

    private static final String AbsolutePath = new File("").getAbsolutePath();
    private static final String FILE_PATH = AbsolutePath+ File.separator+"file"+File.separator+"writeRead"+File.separator+"FileWriteRead.txt";//file/FileWriteRead.txt";

    public static void main(String[] args) {
        //quicklyWrite();
        fileWrite();
        fileRead();
    }

    public static void quicklyWrite(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_PATH, "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    public static void fileWrite() {
        FileWriter fw = null;

        File file = new File(FILE_PATH);

        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try {
            fw = new FileWriter(file);//There will create file.
            fw.write("Use FileWriter write string.\n" +"昵图网_原创素材共享平台");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fileRead() {
        FileReader reader = null;
        char[] buffer = new char[8196];

        try {
            reader = new FileReader(FILE_PATH);
            //reader.read(buffer);
            /*BufferedReader bufferedReader = new BufferedReader(reader);
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }*/
            BufferedReader bu = new BufferedReader(reader);
            String s =null;
            while (( s = bu.readLine()) != null) {
                System.out.println(s);
            }
            //System.out.println(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
