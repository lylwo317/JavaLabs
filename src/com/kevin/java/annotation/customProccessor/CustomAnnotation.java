package com.kevin.java.annotation.customProccessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by kevin on 7/21/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface CustomAnnotation {

    String className();
    String value() default "Hello";
    int type() default 0;
}