package com.kevin.java.jvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by: kevin
 * Date: 2021-12-04
 */
public class TestClassLoader {

    public static class MyClassLoader extends ClassLoader{
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws IOException {
            name = name.replaceAll("\\.","/").concat(".class");
            byte[] b = null;
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(classPath + name);
            int size = inputStream.available();
            b = new byte[size];
            inputStream.read(b);
            return b;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes;
            try {
                bytes = loadByte(name);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
            return defineClass(name, bytes, 0, bytes.length);
        }
    }

    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader("/home/kevin/Workspace/IdeaProjects/JavaLabs/libs/");
        try {
            Class<?> aClass = myClassLoader.loadClass("com.kevin.java.jvm.HelloClassLoader");
            Method sayHello = aClass.getDeclaredMethod("sayHello", null);
            sayHello.invoke(null,null);
            System.out.println(aClass.getClassLoader());
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(String.class.getClassLoader());
//        System.out.println(com.sun.java.swing.SwingUtilities3.class.getClassLoader());
        System.out.println(TestClassLoader.class.getClassLoader());//jdk.internal.loader.ClassLoaders$AppClassLoader@17d99928
    }
}
