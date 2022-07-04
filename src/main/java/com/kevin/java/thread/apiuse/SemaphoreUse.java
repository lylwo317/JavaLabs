package com.kevin.java.thread.apiuse;

import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 操作系统的信号量是个很重要的概念，在进程控制方面都有应用。Java 并发库 的Semaphore 可以很轻松完成信号量控制，
 * Semaphore可以控制某个资源可被同时访问的个数，acquire()获取一个许可，如果没有就等待，而release()释放一个许可。
 * 比如在Windows下可以设置共享文件的最大客户端访问个数。
 * Semaphore维护了当前访问的个数，提供同步机制，控制同时访问的个数。在数据结构中链表可以保存“无限”的节点，
 * 用Semaphore可以实现有限大小的链表。另外重入锁ReentrantLock也可以实现该功能，但实现上要负责些，代码也要复杂些。
 * Created by kevin on 4/22/16.
 */
public class SemaphoreUse extends Thread {
    public static void main(String[] args) {
        int i = 0;
        while (i < 9) {
            i++;
            new SemaphoreUse().start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 控制某资源同时被访问的个数的类 控制同一时间最后只能有3个访问
     */
    static Semaphore semaphore = new Semaphore(3);
    static int timeout = 500;

    public void run() {
        try {
            while (!getConnection()){
//                yield();
            }
            System.out.println("获得一个连接" + Thread.currentThread());
            Thread.sleep(300);
            releaseConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void releaseConnection() {
        /* 释放许可 */
        semaphore.release();
        System.out.println("释放一个连接" + Thread.currentThread());
    }

    public boolean getConnection() {
        /* 获取许可 */
        boolean getAccquire = false;
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
//        throw new IllegalArgumentException("timeout");
    }
}
