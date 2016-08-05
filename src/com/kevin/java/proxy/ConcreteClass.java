package com.kevin.java.proxy;

/**
 * Created by kevin on 7/30/16.
 */
public class ConcreteClass implements TargetInterface {
    public int targetMethodA(int number) {
        System.out.println("开始调用目标类的方法targetMethodA...");
        System.out.println("操作-打印数字:"+number);
        System.out.println("结束调用目标类的方法targetMethodA...");
        return number;
    }

    public int targetMethodB(int number){
        System.out.println("开始调用目标类的方法targetMethodB...");
        System.out.println("操作-打印数字:"+number);
        System.out.println("结束调用目标类的方法targetMethodB...");
        return number;
    }
}
