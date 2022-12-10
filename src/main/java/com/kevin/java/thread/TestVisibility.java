package com.kevin.java.thread;

/**
 * Created by: kevin
 * Date: 2022-12-08
 */
public class TestVisibility {

    private static /*volatile*/ boolean isOk = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                isOk = false;
            }
        });
        thread1.start();
        check();

    }

    public static void check() {
        while (isOk) {

        }
    }
}
/*
 * 产生上面可见性问题根本原因是:
 * 1. C2编译器会做一些不可靠的激进优化，以使得热点代码运行效率最优。这里的优化就是把while(isOK) 优化成 while(true)
 *
 * 以下两点是触发C2的原因：
 * 1. 默认JVM（jdk8）是混合模式，并且开启来分层编译。
 * 2. check中while循环次数很快就触发jit编译，并且触发了C2的编译
 *
 * 因此要解决这个问题可以有以下两种方案：
 * 1. 使用volatile。volatile变量会禁止编译器优化该变量相关读写代码，确保该变量的可见性
 * 2. 禁止C2编译。将分层编译最高等级设置成4级（C2）以下，不包含4级。这样就不会触发C2编译器生成汇编代码，或者以解释模式执行（-Xint）
 */


//jvm运行参数说明
//-XX:TieredStopAtLevel=1 //设置分层编译等级，0 是 解释执行，1-3 都是 C1， 4 是 C2
//-XX:+UnlockDiagnosticVMOptions //解锁检测相关选项
//-XX:PrintAssemblyOptions=intel //用intel的格式打印汇编
//-XX:+PrintAssembly //打印jit汇编
//-XX:+LogCompilation //开启编译日志
//-XX:LogFile=jit.log //设置生成的日志文件路径

//private static boolean isOk = true;
