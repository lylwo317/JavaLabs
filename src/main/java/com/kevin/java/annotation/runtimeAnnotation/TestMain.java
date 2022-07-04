package com.kevin.java.annotation.runtimeAnnotation;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by kevin on 8/6/16.
 */
@HelloAnnotation(say = "Do it!")
public class TestMain {

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloAnnotation annotation = TestMain.class.getAnnotation(HelloAnnotation.class);
        System.out.println(annotation.say());
    }
}
