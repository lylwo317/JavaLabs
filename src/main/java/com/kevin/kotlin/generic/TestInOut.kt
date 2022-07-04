package com.kevin.kotlin.generic

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
private open class Parent1
private open class Parent2 : Parent1()
private open class Parent3 : Parent2()

private open class T : Parent3()

private open class Child1 : T()
private open class Child2 : Child1()
private class Child3 : Child2()

private open class C
private open class B : C()
private class A : B()

private class Example<T>

internal object TestExtend {
  @JvmStatic
  fun main(args: Array<String>) {
    // extendsT中存放的其实是T的其中一个子类或者T本身，如果我们去添加元素，其实不知道到底应该添加T或者T的哪个子类，
    // 这个时候，在进行强转的时候，肯定会出错。但是如果是从集合中将元素取出来，我们则可以知道取出来的元素肯定是T类型（全是它的子类或者T）。
    val extendsT: List<out T> = ArrayList()
    //        extendsT.add(new T());
//        extendsT.add(new Child1());
//        extendsT.add(new Parent1());

    // superT中存的是T或者T的父类型中的一种，那么只要是T或者T的子类肯定能放进去
    val superT: MutableList<in T> = ArrayList()
    superT.add(T())
    superT.add(Child1())
    superT.add(Child2())
    superT.add(Child3())
    //        superT.add(new Parent3());
    run {
      val testAA: Example<out A> = Example()
      //            Example<? extends A> testAB = new Example<B>();//报错
//            Example<? extends A> testAC = new Example<C>();//报错
      val testBA: Example<out B> = Example<A>()
      //            Example<? extends B> testBC = new Example<C>();//报错
      val testCA: Example<out C> = Example<A>()
      val testCB: Example<out C> = Example<B>()
    }
    run {
      val testAA: Example<in A> = Example()
      val testAB: Example<in A> = Example<B>()
      val testAC: Example<in A> = Example<C>()
      //            Example<? super B> testBA = new Example<A>();//报错
      val testBC: Example<in B> = Example<C>()
    }
  }
}
