package com.kevin.kotlin.extension_method

import java.util.concurrent.atomic.AtomicReference

/**
 * Created by: kevin
 * Date: 2023-04-28
 */

interface TestInterface{

}

fun TestInterface(): TestInterface?{
    return null
}

class MyClass {
    fun someFunction() {
        println("Original function")
    }
}

fun MyClass.extensionFunction() {
    println("Extension function")
}

fun main() {
    val obj = MyClass()
    obj.extensionFunction() // 调用扩展函数

    val functionObject: MyClass.() -> Unit = MyClass::extensionFunction
    obj.functionObject() // 使用函数对象调用扩展函数

    TestInterface()?.launch1 {

    }

    TestInterface()?.launch2 {

    }

}

fun TestInterface.launch1(block: () -> Unit){
//    block.test1()
}

//函数的扩展函数
fun (() -> Unit).test1(): Unit{
    println("test1")
}

fun TestInterface.launch2(block: TestInterface.() -> Unit){
//    block.test2()
}

//扩展函数的扩展函数
fun <T> (T.() -> Unit).test2(): Unit{
    println("test2")
}
