package com.kevin.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by kevin on 7/30/16.
 */
public class PersonInvocationHandler implements InvocationHandler {
    private Object concreteClass;

    public PersonInvocationHandler(Object concreteClass){
        this.concreteClass=concreteClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy:"+proxy.getClass().getName());
        System.out.println("method:"+method.getName());
        System.out.println("args:"+args[0]);
        System.out.println("Before invoke "+method.getName()+" method...");
        Object object=method.invoke(concreteClass, args);//普通的Java反射代码,通过反射执行某个类的某方法
        System.out.println("After invoke "+method.getName()+" method...\n");
        return object;
    }
}
