package com.kevin.java.thread.unsafe;

/**
 * Created by kevin on 8/29/16.
 */
public class UnsafePublish {

    public Holder h = new Holder(23);

    public static class Holder {
        private final int n;

        public Holder(int n) { this.n = n; }

        public void assertSanity() {
            if (n != n)
                throw new AssertionError("This statement is false.");
        }
    }

    public UnsafePublish() {

    }


    public static void main(String[] args) {

        UnsafePublish unsafePublish = new UnsafePublish();

        while (true) {
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unsafePublish.h = new Holder(12);
                    throw new RuntimeException();
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unsafePublish.h.assertSanity();
                    throw new RuntimeException();
                }
            });
            thread2.start();
            thread1.start();
        }
    }
}
