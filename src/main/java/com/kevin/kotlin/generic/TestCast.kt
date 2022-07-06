package com.kevin.kotlin.generic

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
inline fun <reified T> List<*>.asListOfType(): List<T>? =
    if (all { it is T })
        this as List<T> else
        null

fun main() {
    val list: List<*> = mutableListOf("Hello")
    val asListOfType = list.asListOfType<Int>()
    var firstOrNull = asListOfType?.firstOrNull()
}