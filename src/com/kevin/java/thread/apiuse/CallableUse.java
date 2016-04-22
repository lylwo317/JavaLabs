package com.kevin.java.thread.apiuse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by kevin on 4/21/16.
 */
public class CallableUse {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        //创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            //使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            //将任务执行结果存储到List中
            resultList.add(future);
        }

        //遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println("打印各个线程（任务）执行的结果"+Thread.currentThread().getName());
                System.out.println(fs.get());     //打印各个线程（任务）执行的结果
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
                executorService.shutdown();
            }
        }
    }

    static class TaskWithResult implements Callable<String> {
        private int id;

        public TaskWithResult(int id) {
            this.id = id;
        }

        /**
         * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
         *
         * @return
         * @throws Exception
         */
        public String call() throws Exception {
            System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
            //一个模拟耗时的操作
            Thread.sleep(1000);
            return"call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
        }
    }

}
