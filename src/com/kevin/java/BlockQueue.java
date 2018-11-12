package com.kevin.java;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by kevin on 17-9-30.
 */
public class BlockQueue {
    public static void main(String[] args) {
        TreeSet<Push> pushTreeSet = new TreeSet<>();
        pushTreeSet.add(new Push(23));
        pushTreeSet.add(new Push(3));
        pushTreeSet.add(new Push(13));
        pushTreeSet.add(new Push(23));
        System.out.printf(Arrays.toString(pushTreeSet.toArray()));

        BlockingQueue blockingQueue = new LinkedBlockingQueue();

        Runnable collectRunnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    Thread.sleep(5000);
                    for (int i = 0; i < 5; i++) {
                        //blockingQueue.add(new Push())
                        //pushTreeSet.add(new Push(random.nextInt(100)));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

    }

    public static class Push implements Comparable<Push>{

        public Push(int id) {
            this.id = id;
        }

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        @Override
        public int compareTo(Push o) {
            return id - o.getId();
        }

        @Override
        public String toString() {
            return "id:" + id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Push push = (Push) o;

            return id == push.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
