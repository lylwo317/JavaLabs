package com.kevin.java.generic;

/**
 * Created by kevin on 4/22/16.
 */
public class GenericTest<T> {

    private T value;

    public static void main(String[] args) {
        GenericTest<? extends String> genericTest = new GenericTest<>();
        //genericTest.setValue("hello");
        String s = genericTest.getValue();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
