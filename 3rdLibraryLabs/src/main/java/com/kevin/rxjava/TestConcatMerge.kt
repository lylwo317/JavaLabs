package com.kevin.rxjava

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit

/**
 * Created by: kevin
 * Date: 2023-02-15
 */
// 先创建三个不同类型的示例 observable
private val observable = Observable.fromArray(1, 2, 3)
    .concatMap { t -> Observable.just(t).delay(1000, TimeUnit.MILLISECONDS) }

private val observable1 = Observable.just("a", "b", "c")
    .concatMap{ t -> Observable.just(t).delay(400, TimeUnit.MILLISECONDS) }

private val observable2 = Observable.just(1.0f, 2.0f, 3.0f)
    .concatMap{ t -> Observable.just(t).delay(1000, TimeUnit.MILLISECONDS) }


fun testMerge(){
    Observable.merge(observable, observable1)
        .subscribe { t ->
            if (t is Float || t is String || t is Int) {
                print("$t ,")
            }
        }
}

fun testConcat(){
    Observable.concat(observable, observable1)
        .subscribe { t ->
            if (t is Float || t is String || t is Int) {
                print("$t ,")
            }
        }
}

fun testUrl(){
    Observable.just("file1", "file2", "file3")
        .flatMap(object : Function<String, Observable<String>> {
            override fun apply(t: String): Observable<String> {
                //上传文件的请求
                return Observable.just("$t request")
            }
        }).concatMap(object : Function<String, Observable<String>> {//ApiRespondJava
            override fun apply(t: String): Observable<String> {
/*
                if (success) {
                    //下发资源值请求
                } else {
                    //Ob.just()
                }
*/
                return Observable.empty()
            }
        }).subscribe()
}


fun main() {
//    testMerge()
    testConcat()
    Thread.sleep(10000)
}