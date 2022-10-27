package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-06
 */

class DataProvider

/**
 * 对象声明，本质就是个单例
 */
object DataProviderManager {
    fun registerDataProvider(provider: DataProvider) {
        // ...
    }
}

fun foo2(a: Int) {
    fd {
        if (a == 0) {
            println("fd a == 0")
            return // 错误：不能使 `foo2` 在此处返回
        } else {
            println("fd a != 0")
        }
    }

    println("fd out")
}

inline fun fd(body: () -> Unit) {
    run {
        println("okio")
        body()
    }
//    val f = object: Runnable {
//        override fun run() = body()
//    }
    // ……
}

fun printHello(name: String?): Unit {
//    fun sayOk() {//匿名内部类
//        name
//        println("Ok")
//    }

    val mutableListOf = mutableListOf<Int>(1, 2, 3, 3)
    run {
        mutableListOf.forEach {
            println(it)
            if (it == 3) {
                return
            }
        }
    }

    if (name != null)
        println("Hello $name")
    else
        println("Hi there!")
    // `return Unit` 或者 `return` 是可选的
}

val stringPlus: (String, String) -> String = String::plus

//public final class DataProviderManager {
//    @NotNull
//    public static final DataProviderManager INSTANCE;
//
//    public final void registerDataProvider(@NotNull DataProvider provider) {
//        Intrinsics.checkNotNullParameter(provider, "provider");
//    }
//
//    private DataProviderManager() {
//    }
//
//    static {
//        DataProviderManager var0 = new DataProviderManager();
//        INSTANCE = var0;
//    }
//}

fun main() {
    foo2(2)
    printHello("Ok")
    stringPlus.invoke("o", "s")
    stringPlus("o","d")
    DataProviderManager.registerDataProvider(DataProvider())
}