package com.kevin.java.thread.apiuse;

import java.util.concurrent.*;

/**
 * Created by kevin on 4/21/16.
 */
public class ThreadPoolUse {
    public static void main(String[] args) {
        // 创建一个线程池，该线程池最大可以有5个线程（worker），其中核心线程数量是3
        // 1. 如果设置keepAliveTime=0的话，当线程数量>corePoolSize，不会回收>corePoolSize的空闲线程
        // 2. 如果设置keepAliveTime>0的话，当线程数量>corePoolSize，不会回收>corePoolSize的空闲线程
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2, //核心线程数量，
                5,//最大线程数量
                10L,//线程闲置时间（空置超时时间），默认只对>corePoolSize有效
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        threadPool.allowCoreThreadTimeOut(true);//设置了这个，就会按照线程池数量
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            threadPool.execute(() -> {
                for(int i1 = 1; i1 < 100; i1++) {
                    try {
                        Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (Thread.interrupted()) {
                        break;
                    }
                    System.out.println("任务" + taskID + "的第" + i1 + "次执行");
                }
            });
        }
        threadPool.shutdown();// 任务执行完毕，关闭线程池
    }
}
