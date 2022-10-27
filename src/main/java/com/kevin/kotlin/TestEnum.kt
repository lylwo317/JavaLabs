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

fun main() {
    printAllValues<Color>()
    var enumValueOf = enumValueOf<Color>("RED")
    var valueOf = Color.valueOf(Color.RED.name)
    var values = Color.values()
//    EnumClass.values(): Array<EnumClass>
    ProtocolState.WAITING.signal()
}