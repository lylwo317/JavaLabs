package com.kevin.java.dynamicProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        MainActivity mainActivity = new MainActivity(new MyService());

        mainActivity.runServiceFun();

        System.out.println("------start proxy hook------");

        proxyHook(mainActivity);

        mainActivity.runServiceFun();
    }

    public static class MyInvocationHandler implements InvocationHandler {

        private static final String TAG = "MyInvocationHandler";

        private IService service;

        public MyInvocationHandler(IService service) {
            this.service = service;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            //Log.i(TAG, "invoke: before");
            System.out.println("before invoke " + method.getName());
            Object result = method.invoke(service, objects);
            System.out.println("after invoke " + method.getName());
            //Log.i(TAG, "invoke: after");
            return result;
        }
    }

    static void proxyHook(MainActivity mainActivity) {
        try {
            Class<? extends MainActivity> aClass = MainActivity.class;
            Field field = aClass.getDeclaredField("iService");
            field.setAccessible(true);
            IService value = (IService) field.get(mainActivity);

            InvocationHandler handler = new MyInvocationHandler(value);
            ClassLoader classLoader = value.getClass().getClassLoader();
            Object instance = Proxy.newProxyInstance(classLoader, value.getClass().getInterfaces(), handler);

            field.set(mainActivity, instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
