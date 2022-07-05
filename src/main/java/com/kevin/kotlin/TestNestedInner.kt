package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-05
 */


interface ClickListener{
    fun onClick()
}

class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }

    inner class Inner {
        private val bar: Int = 1
        fun foo() = this@Outer.bar
    }

    fun addListener(click: ClickListener) {

    }
}

val demo = Outer.Nested().foo() // == 2

interface OuterInterface {
    class NestedClass
    interface NestedInterface
}

class OuterClass {
    class NestedClass
    interface NestedInterface
}

fun main() {

    val outer = Outer()
    outer.addListener(object : ClickListener {
        override fun onClick() {
            println("Click!")
        }
    })
}