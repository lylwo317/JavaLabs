package com.kevin.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by kevin on 7/30/16.
 */
public class DynamicProxyExample {

    public static void main(String[] args){
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Student student=new Student();//元对象(被代理对象)
        InvocationHandler ih=new PersonInvocationHandler(student);//代理实例的调用处理程序。
        //创建一个实现业务接口的代理类,用于访问业务类(见代理模式)。
        //返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序，如ProxyHandler。
        PersonInterface personInterface =
                (PersonInterface) Proxy.newProxyInstance(DynamicProxyExample.class.getClassLoader(),new Class[]{PersonInterface.class},ih);
        //调用代理类方法,Java执行InvocationHandler接口的方法.
        personInterface.say("Hi");
        personInterface.look("teacher");
    }
}
