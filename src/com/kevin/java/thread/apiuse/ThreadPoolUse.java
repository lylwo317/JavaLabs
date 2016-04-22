package com.kevin.java.thread.apiuse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kevin on 4/21/16.
 */
public class ThreadPoolUse {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            threadPool.execute(() -> {
                for(int i1 = 1; i1 < 100; i1++) {
                    /*try {
                        Thread.sleep(20);// 为了测试出效果，让每次任务执行都需要一定时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
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
