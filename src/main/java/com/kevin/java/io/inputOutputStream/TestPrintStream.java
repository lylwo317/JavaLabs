package com.kevin.java.io.inputOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by: kevin
 * Date: 2022-11-30
 */
public class TestPrintStream {

    public static void main(String[] args) {

        // 测试write(), print(), println(), printf()等接口。
        testPrintStreamAPIS() ;
    }



    /**
     * 测试write(), print(), println(), printf()等接口。
     */
    private static void testPrintStreamAPIS() {
        // 0x61对应ASCII码的字母'a'，0x62对应ASCII码的字母'b', ...
        final byte[] arr={0x61, 0x62, 0x63, 0x64, 0x65 }; // abced
        try {
            // 创建文件对应FileOutputStream
            PrintStream out = new PrintStream("data/other.txt");

            // 将字符串“hello PrintStream”+回车符，写入到输出流中
            out.println("hello PrintStream");
            // 将0x41写入到输出流中
            // 0x41对应ASCII码的字母'A'，也就是写入字符'A'
            out.write(0x41);
            // 将字符串"65"写入到输出流中。
            // out.print(0x41); 等价于 out.write(String.valueOf(0x41));
            out.print(0x41);
            // 将字符'B'追加到输出流中
            out.append('B');

            // 将"CDE is 5" + 回车  写入到输出流中
            String str = "CDE";
            int num = 5;
            out.printf("%s is %d\n", str, num);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}