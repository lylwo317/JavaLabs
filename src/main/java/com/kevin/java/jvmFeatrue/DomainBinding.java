package com.kevin.java.jvmFeatrue;

/**
 * Created by kevin on 5/18/16.
 * 动态绑定满足三个条件：
 * 1。需要有继承的存在。
 * 2。需要有方法的重写
 * 3。父类引用指向子类对象。
 * static方法和final方法,private声明的方法和成员变量不能被子类继承，
 * 所有的private方法都被隐式的指定为final的(由此我们也可以知道：
 * 将方法声明为final类型的一是为了防止方法被覆盖，二是为了有效的关闭java中的动态绑定)
 * 除了final，static，private和构造方法是前期绑定外，其他的方法全部为动态绑定
 */
public class DomainBinding {

    static class Father{
        public void f1(){
            System.out.println("Father.f1()");
        }
        public void f1(int i){
            System.out.print("Father.f1(int i)");
        }

        public void f1(Object o){
            System.out.print("Father.f1(Object o)");
        }

        public void f1(String s){
            System.out.print("Father.f1(String s)");
        }

       /* public void f1(Boolean b){
            System.out.printf("Father.f1(Boolean b)");
        }*/
    }

    static class Son extends Father{
        @Override
        public void f1() {
            System.out.print("SonMother.f1()");
        }

        public void f1(char c){
            System.out.print("SonMother.f1(char c)");
        }

        @Override
        public void f1(int i){
            System.out.print("SonMother.f1(int i)");
        }
    }


    public static void main(String[] args) {
        Father s = new Son();
        s.f1(12);
    }
}
