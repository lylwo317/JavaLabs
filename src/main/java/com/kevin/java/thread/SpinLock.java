package com.kevin.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁实现
 * Created by kevin on 4/20/16.
 */
public class SpinLock {

    private final AtomicReference<Thread> _lock = new AtomicReference<>(null);
    private final Lock _unlock = new Lock();
    public Lock lock()
    {
        Thread thread = Thread.currentThread();
        while(true)
        {
            //通过CAS操作来获取锁
            if (!_lock.compareAndSet(null,thread))
            {   //不支持重入
                if (_lock.get()==thread)
                    throw new IllegalStateException("SpinLock is not reentrant");
                continue;
            }
            return _unlock;
        }
    }
    public boolean isLocked()
    {
        return _lock.get()!=null;
    }
    public boolean isLockedThread()
    {
        return _lock.get()==Thread.currentThread();
    }
    public class Lock implements AutoCloseable
    {   //在try catch块中可以自动释放锁，不需要显示手动调用 。
        @Override
        public void close()
        {
            _lock.set(null);
        }
    }

    public static class TestSpinLock implements Runnable {
        private SpinLock spinLock=new SpinLock();
        public int counter=0;
        private void incr(){
            try(SpinLock.Lock lock=spinLock.lock()) {//保证任何时候只有一个线程对counter进行自增操作
                counter++;
            }
        }
        public static void main(String[]args) throws InterruptedException {
            TestSpinLock testSpinLock=new TestSpinLock();
            ExecutorService executorService= Executors.newCachedThreadPool();
            //提交三个任务，来同时计数
            executorService.submit(testSpinLock);
            executorService.submit(testSpinLock);
            executorService.submit(testSpinLock);
            //等待任务完成
            executorService.shutdown();
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
            System.out.println(testSpinLock.counter);
        }
        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                incr();
            }
        }
    }

}



