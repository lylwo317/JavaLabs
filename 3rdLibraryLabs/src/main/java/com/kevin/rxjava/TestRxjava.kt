package com.kevin.rxjava

import io.reactivex.Flowable

/**
 * Created by: kevin
 * Date: 2022-11-01
 */

fun main() {
   Flowable.just("Hello RxJava")
       .subscribe {
           println(it)
       }
}