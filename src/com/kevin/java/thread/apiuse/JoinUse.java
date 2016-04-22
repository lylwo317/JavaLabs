package com.kevin.java.thread.apiuse;

/**
 * Created by kevin on 4/21/16.
 */
public class JoinUse extends Thread {
    public static int a = 0;

    public void run() {
        synchronized (Thread.currentThread()) {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int k = 0; k < 5; k++) {
                a = a + 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new JoinUse();
        t.start();
        t.join(); //加入join()
        System.out.println(a);
    }
}
