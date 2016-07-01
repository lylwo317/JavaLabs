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
                        Thread.sleep(50);
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
                        Thread.sleep(50);
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
        private int  a = 0;
        private volatile int  d = 0;
        private long b = 0;

        void set() {
            a =  1;
            d =  1;
            b = -1;
        }

        boolean check() {
            return ((b ==  0) ||
                    (b == -1 && a == 1 && d == 1));
        }
    }

}
