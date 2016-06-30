package com.kevin.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 4/22/16.
 */
public class GenericTest<T> {

    private T value;

    public static void main(String[] args) {

        /**
         * PECS: producer-extends, consumer-super
         */
        GenericTest<? super List> genericTest = new GenericTest<>();
        List<String> sf = new ArrayList<>();
        genericTest.setValue(sf);
        /*GenericTest<? extends String> genericTest1 = new GenericTest<>();
        String s = genericTest1.getValue();*/
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        System.out.println("setValue:value = "+this.value);
    }
}
