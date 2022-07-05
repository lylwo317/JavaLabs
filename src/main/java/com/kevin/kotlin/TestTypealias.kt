package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
typealias StringList = java.util.ArrayList<String>

typealias MyHandler = (Int, String, Any) -> Unit

fun main() {
    val list = StringList()
    val myHandler: MyHandler = fun(i: Int, s: String, any: Any) {
        println("In fun")
        return
    }
}