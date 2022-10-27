package com.kevin.sample;

/**
 * Created by: kevin
 * Date: 2022-07-06
 */
public class TestObjectExpressions {
    public static void main(String[] args) {
        Object helloWorld = new Object() {
            private final String hello = "Hello";
            private final String world = "World";

            public final String getHello() {
                return this.hello;
            }

            public final String getWorld() {
                return this.world;
            }

            public String toString() {
                return this.hello + ' ' + this.world;
            }
        };
    }
}
