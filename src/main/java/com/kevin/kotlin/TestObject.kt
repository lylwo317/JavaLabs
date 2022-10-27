package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}

fun main() {
    val myClass = MyClass()
    val factory = MyClass
    factory.create()
}