package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2023-02-10
 */

fun testArray(){
    val array = arrayOf(1L)
    var get = array[0]
    var iterator = array.iterator()
}

fun testLongArray(){
    var java = LongArray::class.java
    val longArray2:LongArray? = null
    val longArray = longArrayOf(2L)
    var l = longArray[0]
    var iterator = longArray.iterator()
}

fun main() {
   testArray()
   testLongArray()
}