package com.kevin.java.thread.apiuse;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by kevin on 16-7-1.
 */
public class BlockingQueueUse {

    public static void main(String[] argv) throws Exception {
        int capacity = 10;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(capacity);

        int numWorkers = 2;
        Worker[] workers = new Worker[numWorkers];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker(queue);
            workers[i].start();
        }

        for (int i = 0; i < 100; i++) {
            queue.put(i);
        }
    }

    static class Worker extends Thread {
        BlockingQueue<Integer> q;

        Worker(BlockingQueue<Integer> q) {
            this.q = q;
        }

        public void run() {
            try {
                while (true) {
                    Integer x = q.take();
                    if (x == null) {
                        break;
                    }
                    System.out.println(x);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
