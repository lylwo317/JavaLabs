package com.kevin.java.thread.apiuse;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kevin on 4/22/16.
 */
public class ReentrantLockUse {
    private final ReentrantLock lock = new ReentrantLock();

    public void untimed() {
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            lock.lock();
            System.out.println("acquires lock()");
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
                System.out.println("unlock");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLockUse al = new ReentrantLockUse();
        //al.untimed(); // True -- 可以成功获得锁
        //al.timed(); // True --可以成功获得锁
        //新创建一个线程获得锁并且不释放
        new Thread() {
            {
                setDaemon(true);
            }

            public void run() {
                al.lock.lock();
                al.lock.tryLock();
                System.out.println("acquired");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    al.lock.unlock();
                    al.lock.unlock();
                }

            }
        }.start();
        Thread.sleep(100);// 保证新线程能够先执行
        al.untimed(); // False -- 马上中断放弃
        al.timed(); // False -- 等两秒超时后中断放弃
    }
}
