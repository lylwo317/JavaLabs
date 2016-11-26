package com.kevin.java.thread.unsafe;

import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 9/7/16.
 */
public class ForeverWhile {

    public static boolean stopRequest;

    public static synchronized void requestStop() {
        stopRequest = true;
    }

    public static synchronized boolean stopRequest() {
        return stopRequest;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequest()) {
                    i++;
                }
            }
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        stopRequest = true;
    }
}
