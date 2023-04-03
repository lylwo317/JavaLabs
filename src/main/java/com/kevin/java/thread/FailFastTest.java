package com.kevin.java.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

/**
 * 快速失败机制(可以CopyOnWriteArrayList避免)
 * Created by kevin on 4/21/16.
 */
public class FailFastTest {
    private static final List<Integer> list = new ArrayList<>();

    /**
     * @desc:线程one迭代list
     * @Project:test
     * @file:FailFastTest.java
     * @Authro:chenssy
     * @data:2014年7月26日
     */
    private static class threadOne extends Thread{
        public void run() {
            for (Integer i : list) {
                System.out.println("ThreadOne 遍历:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @desc:当i == 3时，修改list
     * @Project:test
     * @file:FailFastTest.java
     * @Authro:chenssy
     * @data:2014年7月26日
     */
    private static class threadTwo extends Thread{
        public void run(){
            int i = 0 ;
            while(i < 6){
                System.out.println("ThreadTwo run：" + i);
                if(i == 3){
                    list.remove(i);
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0 ; i < 10;i++){
            list.add(i);
        }
        new threadOne().start();
        new threadTwo().start();
    }
}
