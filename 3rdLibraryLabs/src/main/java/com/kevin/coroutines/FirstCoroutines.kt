package com.kevin.coroutines

import kotlinx.coroutines.*
import retrofit2.http.GET
import java.lang.NullPointerException
import kotlin.coroutines.*

/**
 * Created by: kevin
 * Date: 2023-04-28
 */
fun main(){
//    test1()
//    test2()
//    test3()
//    test4()
//    testCancel()
//    testCoroutineExceptionHandler()
    start()
}

fun start(){
    val continuation = suspend {
        println("In Coroutine.")
        5
    }.startCoroutine(object : Continuation<Int> {
        override fun resumeWith(result: Result<Int>) {
            println("Coroutine End: $result")
        }
        override val context = EmptyCoroutineContext
    })
}

fun createAndStart(){
    val continuation = suspend {
        println("In Coroutine.")
        5
    }.createCoroutine(object : Continuation<Int> {
        override fun resumeWith(result: Result<Int>) {
            println("Coroutine End: $result")
        }
        override val context = EmptyCoroutineContext
    })

    continuation.resumeWith(Result.success(Unit))
}

fun testCoroutineExceptionHandler() = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val launch = scope.launch(CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
    }) {
        launch {
            delay(3000)
            throw RuntimeException()
        }
    }

    launch.join()
}

fun testCancel() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
            delay(2000L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}


/*
@Nullable
public final Object invokeSuspend(@NotNull Object $result) {
    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    switch (this.label) {
        case 0:
        ResultKt.throwOnFailure($result);
        CoroutineScope $this$runBlocking = (CoroutineScope) this.L$0;
        BuildersKt.launch$default($this$runBlocking, (CoroutineContext) null, (CoroutineStart) null, new C00021(null), 3, (Object) null);
        this.label = 1;
        if (CoroutineScopeKt.coroutineScope(new C00032(null), (Continuation) this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        break;
        case 1:
        ResultKt.throwOnFailure($result);
        break;
        default:
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
    System.out.println((Object) "Coroutine scope is over");
    return Unit.INSTANCE;
}
*/

fun test4() = runBlocking {//class FirstCoroutinesKt$test2$1
    withContext(Dispatchers.IO) {
        Thread.sleep(10000)
        println("World!")
    }
    println("Hello,")
}

fun test3() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)//
        println("Task from runBlocking")
    }

    coroutineScope { // 创建一个协程作用域，等待其协程体以及所有子协程结束。
/*
        public final Object invokeSuspend(@NotNull Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                ResultKt.throwOnFailure($result);
                CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                BuildersKt.launch$default($this$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00041(null), 3, (Object) null);
                this.label = 1;
                if (DelayKt.delay(100L, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
                case 1:
                ResultKt.throwOnFailure($result);
                break;
                default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            System.out.println((Object) "Task from coroutine scope");
            return Unit.INSTANCE;
        }
*/
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(1000L)//
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}

/**
 * 结构化并发
 */
fun test2() = runBlocking {//class FirstCoroutinesKt$test2$1
    launch {
        delay(10000L)
        println("World!")
    }
    println("Hello,")
}


fun test1() = runBlocking {
    var coroutine = GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }
    println("Hello,") // 协程已在等待(delay)时，主线程还在继续
//    delay(20000L)  // ……我们延迟 2 秒来保证 JVM 的存活
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    coroutine.join()
}

fun test() = runBlocking<Unit> { // 开始执行主协程
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L)
        println("World!")
    }
    println("Hello,") // 主协程在这里会立即执行
    delay(2000L)      // 延迟 2 秒来保证 JVM 存活
}

//interface TestServer {
//    @GET("banner/json")
//    suspend fun awaitBanner(): String
//}
//interface TestServerV2