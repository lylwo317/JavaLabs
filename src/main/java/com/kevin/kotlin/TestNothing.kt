package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-05
 */

fun <T> emptyList(): ArrayList<out T> = ArrayList<Nothing>()

//internal object EmptyList : ArrayList<Nothing>() {
//
//}

fun iWillAlwaysThrowException() : Nothing =  throw Exception("Unnecessary Exception")

fun test1():Nothing{
    while (true){}
}

fun getAddress(addr: String?): String{
    val d = addr ?: iWillAlwaysThrowException()
    return d
}

fun main() {
    val emptyList = emptyList<String>()
    if (emptyList.isEmpty()) {
        println("Is Empty")
    }
}