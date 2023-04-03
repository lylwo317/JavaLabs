package com.kevin.kotlin

import java.lang.reflect.Type

/**
 * Created by: kevin
 * Date: 2022-07-05
 */

inline fun <reified T> Any.asType(): T? {
    return if (this is T) {
        this
    } else {
        null
    }
}

class Bundle{
    fun putInt(key: String, value: Int) {
        println("Bundle.putInt key=$key;value=$value")
    }

    fun putLong(key: String, value: Long) {

    }

    fun putString(key: String, value: String) {

    }

    fun putChar(key: String, value: Char) {

    }
}


inline fun <reified T> Bundle.plus(key: String, value: T) {
    when(value) {
        is Long -> putLong(key, value)
        is String -> putString(key, value)
        is Char -> putChar(key, value)
        is Int-> putInt(key, value)
    }
}

class Gson{
    fun <T> fromJson(json: String, classOfT: Class<T>): T? {
        return null
    }

}

inline fun <reified T> Gson.fromJson(jsonStr: String) =
    fromJson(jsonStr, T::class.java)

fun main() {
    val age = 100
    val asType = age.asType<String>()
    asType?.let {
        println(it)
    }
    val bundle = Bundle()
    bundle.plus("Hello", "Kotlin")
    bundle.plus("Hello", 'c')
    val gson = Gson()
    var fromJson = gson.fromJson<Bundle>("{}")
}