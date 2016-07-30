package com.kevin.java.performancetTest;
/**
 * Created by kevin on 16-7-10.
 */
public class ForTryAndTryFor {

    public static void main(String[] args) {
        forTry();
        tryFor();
    }

 /*   public static void tryFor() {

        long startTime = System.currentTimeMillis();

        int j = 3;
        try {
            for (int i = 0; i < 1000000; i++) {
                Math.sin(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("tryFor " + (endTime - startTime) + " milliseconds");
    }

    public static void forTry() {

        long startTime = System.currentTimeMillis();


        int j = 3;
        for (int i = 0; i < 1000000; i++) {
            try {
                Math.sin(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("forTry " + (endTime - startTime) + " milliseconds");

    }*/


    //@Benchmark
    public static void tryFor() {
        int j = 3;
        try {
            for (int i = 0; i < 1000000; i++) {
                Math.sin(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Benchmark
    public static void forTry() {
        int j = 3;
        for (int i = 0; i < 1000000; i++) {
            try {
                Math.sin(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}