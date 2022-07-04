package com.kevin.java.thread.unsafe;

public class UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }


    public static void main(String[] args) {
        UnsafeSequence sequence = new UnsafeSequence();

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(sequence.getNext());
                }
            });

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(sequence.getNext());
                }
            });

            thread.start();
            thread1.start();
        }
    }
}