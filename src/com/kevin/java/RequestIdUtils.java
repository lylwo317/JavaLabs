package com.kevin.java;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kevin on 17-11-28.
 */
public class RequestIdUtils {

    private static Random random = new Random();

    static ReentrantLock workerLock = new ReentrantLock();


    private static String STRING_TOKEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRequestId(int uId) {
        StringBuilder builder = new StringBuilder();
        builder.append(uId);
        builder.append("-");
        for (int i = 0; i < 9; i++) {
            int index = random.nextInt(STRING_TOKEN.length());
            builder.append(STRING_TOKEN.charAt(index));
        }

        return builder.toString();
    }


    public static String generateRequestId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(STRING_TOKEN.length());
            builder.append(STRING_TOKEN.charAt(index));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(RequestIdUtils.generateRequestId(924537219));
        System.out.println(RequestIdUtils.generateRequestId());

        System.out.println(Integer.parseInt("1f381",16));


        Condition timeOutCondition = workerLock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    workerLock.lock();
                    timeOutCondition.await(3000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    workerLock.unlock();
                }
                System.out.println(200/100);

            }
        }).start();


    }
}
