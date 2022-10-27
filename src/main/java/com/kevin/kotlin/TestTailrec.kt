package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-07-14
 */
fun main() {
    println(gcd(32, 10))
}

/**
 * 最大公约数，辗转相除法
 */
tailrec fun gcd(a: Int, b: Int): Int {
    println("a = $a， b = $b")
    if (b == 0) {
        return a
    }
    return gcd(b, a % b)
}

//public static final int gcd(int a, int b) {
//    while(true) {
//        String var2 = "a = " + a + ", b = " + b;
//        System.out.println(var2);
//        if (b == 0) {
//            return a;
//        }
//
//        int var10000 = b;
//        b = a % b;
//        a = var10000;
//    }
//}