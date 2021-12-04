package com.kevin.java.jni;

public class TestLocalRefLeak {

    public static native String getOneString();

    public static native void setOneString(String string);

    public static void main(String[] args) {
        System.load("/home/kevin/Workspace/IdeaProjects/JavaLearn/libs/liblocalrefleak.so");

/*        for (int i = 0; i < 100; i++) {
        }*/
        String str = getOneString();



        System.out.println(getOneString());
        //System.out.println();
    }
}
