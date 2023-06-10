package com.kevin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.util.concurrent.CancellationException

/**
 * Created by: kevin
 * Date: 2023-06-13
 */

/**
 * 当消费端suspend阻塞后，直接将略过所有前面的结果。将最后一个结果发给消费端
 */
suspend fun collectLastM(){
    flow {
        (1..9).forEach {
            delay(100)
            emit(it)
        }
    }.collectLatest {
        delay(110)
        println(it)
    }
}

suspend fun conflate(){
    flow {
        (1..9).forEach {
            delay(100)
            emit(it)
        }
    }.conflate().collect {
        delay(300)
        println(it)
    }
}

/**
 * 两个都更新的时候才会更新
 */
suspend fun zipM(){
    val flow1 = (1..5).asFlow()
    val flow2 = flowOf("李白","杜甫","安安安安卓")
    flow1.zip(flow2){a,b->
        "$a : $b"
    }.collect {
        println(it)
    }
}

/**
 * 只要两个flow中有一个更新，并且当前有两个flow中有一个元素更新，就emit。
 */
suspend fun combineM(){
    val flowA = (1..5).asFlow()
    val flowB = flowOf("李白","杜甫","安安安安卓")
    flowA.combine(flowB) { a, b ->
        "$a : $b"
    }.collect {
        println(it)
    }
//    2 : 李白 //flowB刚输出第一个，但是flowA已经更新到第二个了
//    3 : 安安安安卓
//    5 : 安安安安卓
}

suspend fun testFlattenConcat(){
    flow {
        List(5) {
            emit(it)
        }
    }.map { it ->//类似rxJava flatMap
        println("map: $it")
        flow { List(it) { emit(it + 10) } }
    }.flattenConcat()
        .collect { println(it) }
}

suspend fun testFlattenMerge(){
    flow {
        List(5) {
            emit(it)
        }
    }.map { it ->//类似rxJava flatMap
        println("map: $it")
        flow { List(it) { emit(it+10) } }
    }.flattenMerge()
        .collect { println(it) }
}

suspend fun testCallbackFlow(){
    callbackFlow<Int> {
        List(5){
            send(it)
        }//使用完channel要记得关闭
        close()//代表完成/结束
        awaitClose()
    }
        .collect(FlowCollector {
            println(it)
        })

}

private fun testSharedFlowCapacity() = runBlocking {
    val sharedFlow = MutableSharedFlow<Int>(
        replay = 1,//相当于粘性数据
        extraBufferCapacity = 2,//接受的慢时候，发送的入栈
        onBufferOverflow = BufferOverflow.SUSPEND
    )


    launch {
        sharedFlow.collect {
            println("collect1 received ago shared flow $it")
            if (it == 5) {
                cancel()
            }
        }
    }

    launch() {
        (1..5).forEach {
            println("emit1  send ago  flow $it")
            sharedFlow.emit(it)
            delay(1000)
            println("emit1 send after flow $it")
        }
    }

    // wait a minute
//    delay(100)
    launch {
        sharedFlow.collect {
            println("collect2 received shared flow $it")
        }
    }
}

private fun testChannelFlow() = runBlocking{
    //构建
    val channelFlow = channelFlow {
        send("hello")
        withContext(Dispatchers.IO) {
            delay(2000)
            send("channel flow")
        }
    }

    //监听
    launch{
        channelFlow.collect{ value->
            println(value)
        }
    }
}

private suspend fun testCollectIndexed() = runBlocking {
    flowOf(4, 9, 6)
        .collectIndexed { index, value ->
            println(index)
        }
}

private suspend fun testWithIndexed() = runBlocking {
    flowOf(4, 9, 6)
        .withIndex()
        .collect{
            it.index
        }
}

private suspend fun testLaunchIn() = runBlocking {
    val myFlow= flow {
        emit(1)
        emit(2)
    }

    myFlow
        .onEach { println(it) }
        .onCompletion { println("onCompletion") }
        .launchIn(this)
}

private suspend fun testLast() = runBlocking {
    val myFlow= flow {
        emit(1)
        emit(2)
    }

    println(myFlow.last())
    println(myFlow.lastOrNull())
}

private suspend fun testFirst() = runBlocking {
    val myFlow= flow {
        emit(1)
        emit(2)
    }

    println(myFlow.first())
    println(myFlow.firstOrNull())
}

private suspend fun testSingle() = runBlocking {
    val myFlow = flow {
        emit(1)
    }

    launch {
        println(myFlow.single()) // 1
    }

    val myFlow1 = flow {
        emit(1)
        emit(2)
    }

    launch {
        println(myFlow1.single()) // error
    }
}

private suspend fun testSingleOrNull() = runBlocking {
    val myFlow = flow {
        emit(1)
    }

//    launch {
//        println(myFlow.singleOrNull()) // 1
//    }

    val myFlow1 = flow {
        emit(1)
        emit(2)
    }

    launch {
        println(myFlow1.singleOrNull()) // error
        println(myFlow1.count())
    }
}

private suspend fun testFold() = runBlocking {
    val myFlow = flow {
        emit(1)
        emit(2)
    }

    launch {
        val result: Int = myFlow.fold(0) { acc, value ->
            acc + value
        }
        println(result)
    }
}

private suspend fun testReduce() = runBlocking {
    val myFlow = flow {
        emit(1)
        emit(2)
    }

    launch {
        val result: Int = myFlow.reduce { acc, value ->
            acc + value
        }
        println(result)
    }
}

private suspend fun testEmptyFlow() = runBlocking {
    val myFlow = emptyFlow<Int>()

    launch {
        myFlow
            .onEmpty {
                println("onEmpty")
            }
            .onCompletion {
                println("onCompletion")
            }
            .collect()
    }
}

private suspend fun testTransformLatest() = runBlocking {
    val myFlow = flow {
        emit(1)
        emit(2)
    }

    launch {
        myFlow
            .transformLatest {
                delay(100)
                emit(it)
            }
            .collect {
                println(it)//only print 2
            }
    }
}

private suspend fun testAsStateFlow(){
    var asStateFlow = MutableStateFlow<Int>(0).asStateFlow()
//    read only
//    asStateFlow.value = 1
}

/**
 * channel转flow，但是不能重复订阅
 */
private suspend fun testReceiveAsFlow() = runBlocking {
    val _event = Channel<Int>()
    launch {
        _event.send(100)
        _event.send(200)
        _event.send(300)
        _event.close()
    }
    val event = _event.receiveAsFlow()
    val event2 = _event.receiveAsFlow()
    launch {
        println(event.first())//100
        event.collect {
            println(it)//200 300
        }
        event2.collect{
            println("event2 $it")//no value
        }
    }
}

/**
 * channel转flow，但是不能重复订阅
 */
private suspend fun testConsumeAsFlow() = runBlocking {
    val _event = Channel<Int>()
    launch {
        _event.send(100)
        _event.send(200)
        _event.send(300)
        _event.close()
    }
    val event = _event.consumeAsFlow()
    launch {
        println(event.first())//100
        event.collect {
            println(it)//200 300
        }
    }
}

private suspend fun testScan() = runBlocking {
    val myFlow = flow {
        emit(1)
        emit(2)
    }

    launch {
        myFlow
            .scan(0) { accumulator, value ->
                accumulator + value
            }
            .collect {
                println(it)
            }

    }
}

private suspend fun testProduceIn() = runBlocking {
    val myFlow = flow {
        emit(1)
        emit(2)
    }

    launch {
        var produceIn = myFlow
            .produceIn(this)
//            .consumeEach {
//                println(it)
//            }

    }
}

//第一个开始连续满足while条件的去掉，后面的都返回
private suspend fun testDropWhile() = runBlocking {
    flow {
        emit(1) //从首项开始就不满足条件
        emit(2)
        emit(3)
        emit(4)
    }.dropWhile { it == 3  }//从第一项开始满足条件的都drop，直到不满足的
        .collect { value ->
        print(value) //1234
    }
}

/**
 * 连续两个值一样，则跳过发送
 */
private suspend fun testDistinctUntilChanged() = runBlocking {
    flow {
        emit(1)
        emit(2)
        emit(2)//与前一个值一样，跳过
        emit(3)
        emit(4)
        emit(3)
    }.distinctUntilChanged()
        .collect { value ->
            print(value) //1234
        }
}

@ExperimentalCoroutinesApi
private suspend fun testFlatMapContact() = runBlocking {
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(3)
    }.flatMapConcat {
        flowOf("$it map")
    }
        .collect { value ->
            print(value)
        }
}

private suspend fun testCancelable() = runBlocking {
    flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(3)
    }.cancellable()
        .collect { value ->
            print(value)
        }
}

private suspend fun testRetryWhen() = runBlocking {
    flow {
        emit(1)
        emit(2)
        throw IOException()
        emit(3)
        emit(4)
    }.retryWhen { cause, attempt ->
        return@retryWhen attempt <= 2
    }
        .collect { value ->
            print(value)//12121212Exception in thread "main" java.io.IOException
        }
}

private suspend fun testBuffer() = runBlocking {
    flowOf("A", "B", "C")
        .onEach  { println("1$it") }
        .buffer()  // <--------------- buffer between onEach and collect
        .collect { println("2$it") }
}

suspend fun main() {
//    collectLastM()
//    conflate()
//    zipM()
//    combineM()
//    testFlattenConcat()
//    testFlattenMerge()
//    testCallbackFlow()
//    testSharedFlowCapacity()
//    testChannelFlow()
//    testCollectIndexed()
//    testLaunchIn()
//    testLast()
//    testFirst()
//    testSingle()
//    testSingleOrNull()
//    testFold()
//    testReduce()
//    testEmptyFlow()
//    testTransformLatest()
//    testReceiveAsFlow()
//    testConsumeAsFlow()
//    testScan()
//    testProduceIn()
//    testDropWhile()
//    testDistinctUntilChanged()
//    testFlatMapContact()
//    testCancelable()
//    testRetryWhen()
    testBuffer()
}