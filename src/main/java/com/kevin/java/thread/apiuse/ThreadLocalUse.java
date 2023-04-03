package com.kevin.java.thread.apiuse;

/**
 * Created by kevin on 4/20/16.
 */
public class ThreadLocalUse {

    // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
    private static final ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
        public Integer initialValue() {
            return 0;
        }
    };

    // ②获取下一个序列值
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);//seqNum相当于Key。每个Thread都有一个ThreadLocalMap成员对象，里面是以ThreadLocal对象为Key（这里是以seqNum）
        return seqNum.get();
    }

    public static void main(String[] args) {
        ThreadLocalUse sn = new ThreadLocalUse();
        // ③ 3个线程共享sn，各自产生序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        t2.start();
        t3.start();
    }

    private static class TestClient extends Thread {
        private final ThreadLocalUse sn;

        public TestClient(ThreadLocalUse sn) {
            this.sn = sn;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                // ④每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + sn.getNextNum() + "]");
            }
        }
    }
}
