package com.kevin.java.generic;

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
public class TestCast {
    static class Foo{
        <T> void isType(Class<?> type, T data) {
//            Class<T> type = (Class<T>) data.getClass(); //maybe passed to the method
            if (type.isInstance(data) ) {
                T t = (T) type.cast(data);
                System.out.println(t);
                // ...
            }
        }
    }
    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.isType(Integer.class, 1);
    }
}
