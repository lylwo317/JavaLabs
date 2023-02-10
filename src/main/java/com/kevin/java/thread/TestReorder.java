package com.kevin.java.thread;

/**
 * Created by: kevin
 * Date: 2022-12-10
 */
public class TestReorder {

    private static int x = 0, y = 0;

    private static  int a = 0, b = 0;


    public static void main(String[] args) throws InterruptedException{
        int i=0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            /**
             *  x,y:
             *  每次循环，x,y,a,b都会赋初值0，两个线程各执行一次，执行完，x,y可能的值有：
             *  0，1  1，0  1，1   这是我们按正常逻辑思考可能出现的结果，但是有一种情况是只会在重排序的情况出现
             *  就是 0,0   也就是这种情况下两个线程变成了这样的逻辑。
             *   thread1:
             *   x=b;
             *   a=1;
             *   thread2:
             *   y=a;
             *   b=1;
             *   我们执行看下是否会出现这样的情况呢？
             *
             *   造成这个主要原因是因为：Store Buffer 和 Invalid Queue 导致 某个核心对其它核心来说是乱序执行的效果
             *   https://zhuanlan.zhihu.com/p/141655129
             */
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
//                    shortWait(20000);
                    a = 1;
                    x = b;
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            System.out.println("第" + i + "次（" + x + "," + y + ")");
            if (x==0&&y==0){
                break;
            }

        }

    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }
}
