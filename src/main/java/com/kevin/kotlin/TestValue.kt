package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2023-06-07
 */

/*
public final class TestValue {
    @NotNull
    public final String getName() {
        return "OK";
    }

    public final int getAge() {
        return 100;
    }
}
*/

class TestValue {
    val name: String
       get() {
           return "OK"
       }

    val age: Int
        get() {
            return 100
        }
}

fun main() {

}