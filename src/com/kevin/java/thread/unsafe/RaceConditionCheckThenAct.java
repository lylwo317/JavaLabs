package com.kevin.java.thread.unsafe;

/**
 * Created by kevin on 16-6-25.
 * 解决这种资源竞争（竞态条件）问题，就是保证原子性
 */
public class RaceConditionCheckThenAct {//另一种自增操作，read-modify-write;还有延迟初始化。

    private String s = "XiaJiaHua";


    public void checkThenAction() {
        if (s != null) {
            s.length();//检查状态已经失效，因为s有可能已经被其他线程置为null
        }
    }

    public void setStringToNull(){
        s = null;
    }

    public void setStringNotNull() {
        s = "Hello";
    }


    public static void main(String[] args) {
        RaceConditionCheckThenAct nullException = new RaceConditionCheckThenAct();

        for (int i = 0; i < 10000; i++) {
            Thread thread = new Thread(() -> {

                nullException.checkThenAction();

            });

            Thread thread1 = new Thread(() -> {

                nullException.setStringToNull();

                nullException.checkThenAction();

                nullException.setStringNotNull();
            });
            thread.start();

            thread1.start();
        }

    }
}
