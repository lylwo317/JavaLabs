package com.kevin.java.annotation.customProccessor;

/**
 * Created by kevin on 7/21/16.
 */
public class UseProcessor {

    @CustomAnnotation(className = "String", type = 1)
    public void annotatedMethod(String value) {
    }
}
