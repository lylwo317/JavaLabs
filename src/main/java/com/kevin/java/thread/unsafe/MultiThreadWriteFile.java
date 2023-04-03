package com.kevin.java.thread.unsafe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MultiThreadWriteFile {

    private static final String filePath = "hello.txt";


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    writeFile();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    writeFile();
                }
            });

            thread1.start();
            thread2.start();
        }
    }


    public static void writeFile() {
        File writefile = new File(filePath);

        if (!writefile.exists()) {

        }

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(writefile, true);
            fileWriter.write("我bu会写入文件啦" + Thread.currentThread().getName() + "\r\n");
            fileWriter.flush();
            //out.append(); // \r\n即为换行
            //out.flush(); // 把缓存区内容压入文件
            //out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
