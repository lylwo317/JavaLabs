package com.kevin.java.proxy;

/**
 * Created by kevin on 7/30/16.
 */
public class Student implements PersonInterface {



    public int say(int number) {
        return number;
    }

    public int look(int number){
        System.out.println("操作-打印数字:"+number);
        return number;
    }

    @Override
    public String say(String s) {
        System.out.println("say:"+s);
        return s;
    }

    @Override
    public String look(String s) {
        System.out.println("look:"+s);
        return s;
    }
}
