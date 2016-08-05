package com.kevin.java.thread.unsafe;

/**
 * MyNoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */

public class MyNoVisibility {

    public static boolean stop=false;

    public static void main(String[] args) {
        while (!stop) {
            SetCheck setCheck = new SetCheck();
            Thread thread1=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setCheck.set();
                }
            });

            Thread thread2=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!setCheck.check()) {
                        System.out.println("false");
                        stop = true;
                    }
                }
            });
            thread1.start();
            thread2.start();
        }
    }


    public static final class SetCheck {
        private float  a = 0;
        private long c = 0;
        /*private long e = 0;
        private int f = 0;
        private float g = 0;
        private boolean h = false;*/
        private volatile int  d = 0;
        private long b = 0;

        void set() {
            a =  1;
            c = 1;
            /*e = 1;
            f = 1;
            g = 1;
            h = true;*/
            d =  1;
            b = -1;
        }

        boolean check() {
            return ((b ==  0) ||
                    (b == -1 && a == 1 && c == 1 && d == 1));
        }
    }

}
