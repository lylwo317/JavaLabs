package com.kevin.java.thread.unsafe;

import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 9/7/16.
 */
public class FinalExample {

    int i;                            //普通变量
    final int j;                      //final变量
    static FinalExample obj;

    public FinalExample () {     //构造函数
        i = 1;                        //写普通域
        j = 2;                        //写final域
    }

    public static void writer () {    //写线程A执行
        obj = new FinalExample ();
    }

    public static void reader () {       //读线程B执行
        if (obj!=null && obj.i != 1) {
            System.out.println("Unsafe!!!");
        }

    }


    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                FinalExample.writer();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                FinalExample.reader();
            }
        });
        thread1.start();
        thread2.start();
    }
}
