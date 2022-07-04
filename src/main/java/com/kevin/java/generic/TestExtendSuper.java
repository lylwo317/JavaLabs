package com.kevin.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
class Parent1{}
class Parent2 extends Parent1{}
class Parent3 extends Parent2{}

class T extends Parent3{}

class Child1 extends T{}
class Child2 extends Child1{}
class Child3 extends Child2{}

class C {}
class B extends C{}
class A extends B{}

class Example<T>{
}

class TestExtend{
    public static void main(String[] args) {
        // extendsT中存放的其实是T的其中一种子类或者T本身，如果我们去添加元素，其实不知道到底应该添加T或者T的哪个子类，
        // 这个时候，在进行强转的时候，肯定会出错。但是如果是从集合中将元素取出来，我们则可以知道取出来的元素肯定是T类型（全是它的子类或者T）。
        List<? extends T> extendsT = new ArrayList<>();
//        extendsT.add(new T());
//        extendsT.add(new Child1());
//        extendsT.add(new Parent1());

        // superT中存的是T或者T的父类型中的一种，那么只要是T或者T的子类肯定能放进去
        List<? super T > superT = new ArrayList<>();
        superT.add(new T());
        superT.add(new Child1());
        superT.add(new Child2());
        superT.add(new Child3());
//        superT.add(new Parent3());

        {
            Example<? extends A> testAA = new Example<A>();
//            Example<? extends A> testAB = new Example<B>();//报错
//            Example<? extends A> testAC = new Example<C>();//报错
            Example<? extends B> testBA = new Example<A>();
//            Example<? extends B> testBC = new Example<C>();//报错
            Example<? extends C> testCA = new Example<A>();
            Example<? extends C> testCB = new Example<B>();
        }
        {
            Example<? super A> testAA = new Example<A>();
            Example<? super A> testAB = new Example<B>();
            Example<? super A> testAC = new Example<C>();
//            Example<? super B> testBA = new Example<A>();//报错
            Example<? super B> testBC = new Example<C>();
//            Example<? super C> testCA = new Example<A>();//报错
//            Example<? super C> testCB = new Example<B>();//报错
        }
    }
}

