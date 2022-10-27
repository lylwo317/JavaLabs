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

/*
public final class Outer {
    private final int bar = 1;

    public final void addListener(@NotNull ClickListener click) {
        Intrinsics.checkNotNullParameter(click, "click");
    }

    public static final class Nested {
        public final int foo() {
            return 2;
        }
    }

    public final class Inner {
        private final int bar = 1;

        public final int foo() {
            return Outer.this.bar;
        }
    }
}
*/


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