package com.kevin.java.jni;

public class HelloJNI {
    public static void main(String[] args) {
        /*加载library。在属性java.library.path的路径中搜索。
        获取该属性的方法System.getProperty("java.library.path");/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib


        方法一：
        -Djava.library.path=/home/kevin/Workspace/IdeaProjects/JavaLearn/src/com/kevin/java/jni
        System.loadLibrary("HelloJni");

        方法二：
        System.load("/home/kevin/Workspace/IdeaProjects/JavaLearn/src/com/kevin/java/jni/libHelloJni.so");//绝对路径加载*/

        /*
        根据native方法的java文件生成对应的c头文件。
        1. 先进入到src目录
            cd /home/kevin/Workspace/IdeaProjects/JavaLearn/src
        2. 然后执行javah命令生成C头文件
            javah -o ./com/kevin/java/jni/HelloJNI.h com.kevin.java.jni.HelloJNI
         */
        System.loadLibrary("HelloJni");
        System.out.println(getHelloString());
    }


    public static native String getHelloString();

}
