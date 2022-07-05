package com.kevin.kotlin

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        //BiFunction
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        //BiFunction
        override fun apply(t: Int, u: Int): Int = t * u
    };

    //IntBinaryOperator
    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}


inline fun <reified T : Enum<T>> printAllValues() {
    print(enumValues<T>().joinToString { it.name })
}

private val adHoc = object{//只有private才能访问
    var x: Int = 10
    var y: Int = 3
}

fun foo() {
    print(adHoc.x + adHoc.y)
}
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
//        val x2 = publicFoo().x  // 错误：未能解析的引用“x”
    }
}

fun main() {
    printAllValues<Color>()
    var enumValueOf = enumValueOf<Color>("RED")
    var valueOf = Color.valueOf(Color.RED.name)
    var values = Color.values()
//    EnumClass.values(): Array<EnumClass>
    ProtocolState.WAITING.signal()
}