package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-06
 */

open class A(x: Int) {
    open val y: Int = x
}
interface B { /*......*/ }
val ab: A = object : A(1), B {//生成一个匿名内部类
    override val y = 15
}

/*
@Metadata(
    mv = {1, 7, 1},
    k = 2,
    d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\u0004\u001a\u00020\u0005\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"},
    d2 = {"ab", "Lcom/kevin/kotlin/A;", "getAb", "()Lcom/kevin/kotlin/A;", "main", "", "JavaLabs"}
)
public final class TestObjectExpressionsKt {
    @NotNull
    private static final A ab = (A)(new B(1) {
        private final int y = 15;

        public int getY() {
            return this.y;
        }
    });

    @NotNull
    public static final A getAb() {
        return ab;
    }

    public static final void main() {
    }

    // $FF: synthetic method
    public static void main(String[] var0) {
        main();
    }
}
// A.java
package com.kevin.kotlin;

import kotlin.Metadata;

@Metadata(
    mv = {1, 7, 1},
    k = 1,
    d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"},
    d2 = {"Lcom/kevin/kotlin/A;", "", "x", "", "(I)V", "y", "getY", "()I", "JavaLabs"}
)
public class A {
    private final int y;

    public int getY() {
        return this.y;
    }

    public A(int x) {
        this.y = x;
    }
}
*/

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
    val ok = 100
    //var ok = 200 //包装到一个对象的成员里面
    //sampleStart
    val helloWorld = object {//创建了一个匿名类内部类
        val hello = "Hello"
        val world = "World"
        // object expressions extend Any, so `override` is required on `toString()`
        override fun toString() = "$hello $world $ok"
    }
//sampleEnd
//    println(helloWorld.hello)//调用匿名内部类的方法
    println(helloWorld)
}