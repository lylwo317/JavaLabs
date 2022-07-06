package com.kevin.java.thread;

import java.util.concurrent.*;

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
public class TestExecutor {
    public static void main(String[] args) {
        ExecutorService executorService =
                new ThreadPoolExecutor(1, 2,
                        5000L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        while (true) {

        }
    }
}
