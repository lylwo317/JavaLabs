package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
object DataProviderManager {
    fun registerDataProvider(provider: String) {
        // ……
    }

    val age = 10
//
//    val allDataProviders: Collection<String>
//        get() = { mutableListOf<String>("Hello") }
}

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