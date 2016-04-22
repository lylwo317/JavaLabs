package com.kevin.java.thread.apiuse;

/**
 * Created by kevin on 4/21/16.
 */
public class WaitUse {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable{
        @Override
        public void run(){
            synchronized (WaitUse.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting...");
                try {
                    //调用wait()方法，线程会放弃对象锁(WaitUse.class)
                    WaitUse.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on ....");
                System.out.println("thread1 is over!!!");
            }
        }
    }

    private static class Thread2 implements Runnable{
        @Override
        public void run(){
            synchronized (WaitUse.class) {
                System.out.println("enter thread2....");
                System.out.println("thread2 is waiting....");
                System.out.println("notify()");
                //对此对象调用notify()方法后,对应调用该对象wait()方法的线程就会进入等待获取锁的状态（这里是Thread1）
                //需要注意的是，如果有多个线程调用了wait方法，那notify()只会唤醒其中的一个线程。要想唤醒所有线程，需要调用notifyAll()
                WaitUse.class.notify();
                //==================
                //区别
                //如果我们把代码：WaitUse.class.notify();给注释掉，即WaitUse.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
                try {
                    //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，当指定的时间到了又会自动恢复运行状态。
                    //在调用sleep()方法的过程中，线程不会释放对象锁。
                    //Thread.sleep(5000);//方式一：不会释放锁,所以只有Thread2 sleep 结束，然后执行完synchronized块，
                    // Thread1才能获取到锁，不然都会一直处于阻塞状态


                    WaitUse.class.wait();//方式二：会释放锁
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on....");
                System.out.println("thread2 is over!!!");
            }
        }
    }
}
