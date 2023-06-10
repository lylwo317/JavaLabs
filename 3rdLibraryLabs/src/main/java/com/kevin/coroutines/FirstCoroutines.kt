package com.kevin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * Created by: kevin
 * Date: 2023-04-28
 */
suspend fun main(){
//    test1()
//    test2()
//    test3()
//    test4()
    testSelect()
//    testSupervisorJob()
//    testSupervisorJob2()
//    testCancel()
//    testCancel2()
//    testChannel()
//    testCoroutineExceptionHandler()
//    testException()
//    createAndStart()
//    createAndStart2()
//    createAndStart3()
//    callLaunchCoroutine()
//    suspendFunc01(5)
//    suspendFunc02("Hello", "A")
}

//ResultKt.throwOnFailure($result);
//CoroutineScope $this$runBlocking = (CoroutineScope) this.L$0;
//Deferred localDeferred = FirstCoroutinesKt.getUserFromLocal($this$runBlocking, "kevin");
//Deferred remoteDeferred = FirstCoroutinesKt.getUserFromApi($this$runBlocking, "kevin");
//SelectBuilder selectImplementation = new SelectImplementation(getContext());
//SelectBuilder builder = selectImplementation;
//builder.invoke(localDeferred.getOnAwait(), new FirstCoroutinesKt$testSelect$2$userResponse$1$1(null));
//builder.invoke(remoteDeferred.getOnAwait(), new FirstCoroutinesKt$testSelect$2$userResponse$1$2(null));
//this.label = 1;
//obj = selectImplementation.doSelect(this);
//if (obj == coroutine_suspended) {
//    return coroutine_suspended;
//}
suspend fun testSelect() = runBlocking{
    val localDeferred = getUserFromLocal("kevin")
    val remoteDeferred = getUserFromApi("kevin")

    val userResponse = select {
        localDeferred.onAwait {
            it
        }
        remoteDeferred.onAwait {
            it
        }
    }

//    userResponse.isLocal.takeIf { it }?.let {
//        val userFromApi = remoteDeferred.await()
//        cacheUser(login, userFromApi)
//        log(userFromApi)
//    }

    println(userResponse)
}

fun CoroutineScope.getUserFromApi(login: String) = async(Dispatchers.IO){
    delay(2000)
    "From Remote"
}

fun CoroutineScope.getUserFromLocal(login:String) = async(Dispatchers.IO){
    delay(1000)
    "From Local"
}


suspend fun testSupervisorJob2(){
    val scope = CoroutineScope(SupervisorJob())//Job()
    scope.launch {
        delay(1000)
        println("Hello")
        throw RuntimeException()
    }

    scope.launch {
        delay(2000)
        println("world")
    }.join()
}

suspend fun testSupervisorJob(){
    val scope = CoroutineScope(EmptyCoroutineContext)//Job()
    val launch = scope.launch(CoroutineExceptionHandler { _, exception ->
        println("root exception handler")
        exception.printStackTrace()
    }) {//这里是StandaloneCoroutine : Job
        val supervisorScope = CoroutineScope(coroutineContext + SupervisorJob())
        supervisorScope.launch(CoroutineExceptionHandler { _, exception ->
            println("A-a exception handler")
            exception.printStackTrace()
        }) {
            println("A-a start")
            delay(1000)
            println("A-a end")
            throw RuntimeException("A-a")
        }.join()

        supervisorScope.launch(CoroutineExceptionHandler { _, exception ->
            println("A-b exception handler")
            exception.printStackTrace()
        }) {
            println("A-b start")
            delay(2000)
            println("A-b end")
            throw RuntimeException("A-b")
        }.join()

//        launch(SupervisorJob() + CoroutineExceptionHandler { _, exception ->
//            println("A exception handler")
//            exception.printStackTrace()
//        }) {
//            launch(CoroutineExceptionHandler { _, exception ->
//                println("A-a exception handler")
//                exception.printStackTrace()
//            }) {
//                println("A-a start")
//                delay(1000)
//                println("A-a end")
//                throw RuntimeException("A-a")
//            }
//
//            launch(CoroutineExceptionHandler { _, exception ->
//                println("A-b exception handler")
//                exception.printStackTrace()
//            }) {
//                println("A-b start")
//                delay(2000)
//                println("A-b end")
//                throw RuntimeException("A-b")
//            }
//
//            println("A start")
//            delay(3000)
//            println("A end")
//            throw RuntimeException("A")
//        }.join()//由于SupervisorJob的出现，使得这里的job没有父子关系
    }

    launch.join()
}

suspend fun testChannel(){
     val channel = Channel<Int>()

    val producer = GlobalScope.launch {
        var i = 0
        while (true){
            channel.send(i++)
            delay(1000)
        }
    }

    val consumer = GlobalScope.launch {
        while(true){
            val element = channel.receive()
            println(element)
//            Logger.debug(element)
        }
    }

    producer.join()
    consumer.join()
}

class LogInterceptor : ContinuationInterceptor {
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>)
            = LogContinuation(continuation)
}

class LogContinuation<T>(private val continuation: Continuation<T>)
    : Continuation<T> by continuation {
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith: $result")
        continuation.resumeWith(result)
        println("after resumeWith.")
    }
}

suspend fun suspendFunc01(a: Int){
    return
}

suspend fun suspendFunc02(a: String, b: String) = suspendCoroutine<Int> { continuation -> //complete
    thread {
        continuation.resumeWith(Result.success(5)) // ... 1
    }
}

class ProducerScope<T> {
    suspend fun produce(value: T){
//        delay(1000)
        println(value)
    }
}
fun callLaunchCoroutine(){
    launchCoroutine(ProducerScope<Int>()){
        println("In Coroutine.")
        produce(1024)
        delay(1000)
        produce(2048)
    }
}

/**
 * 让协程可以使用receiver中方法和成员。也就是说让协程体做为receiver的扩展函数，这样在协程体里面就可以使用receiver中的成员和方法
 */
fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override fun resumeWith(result: Result<T>) {
            println("Coroutine End: $result")
        }
        override val context = EmptyCoroutineContext
    })
}

suspend fun mySuspendFun(): Int {
    println("In Coroutine.")
    return 5
}
fun createAndStart2(){
    //createCoroutineUnintercepted(completion) 本质上就是为了调用suspend 中的create方法创建continuation
    ::mySuspendFun
        .startCoroutine(object : Continuation<Int> {
        override fun resumeWith(result: Result<Int>) {//类似于回调
            println("Coroutine End: $result")
        }
//        override val context = EmptyCoroutineContext
        override val context = LogInterceptor()
    })
}

fun createAndStart3(){
    //createCoroutineUnintercepted(completion) 本质上就是为了调用suspend 中的create方法创建continuation

    suspend {
        suspendFunc02("Hello", "Kotlin")
        suspendFunc02("Hello", "Coroutine")
    }
        .startCoroutine(object : Continuation<Int> {
            override fun resumeWith(result: Result<Int>) {//类似于回调
                println("Coroutine End: $result")
            }
//            override val context = EmptyCoroutineContext
            override val context = LogInterceptor()
        })
}

fun createAndStart(){
    val continuation = suspend { //FirstCoroutinesKt$createAndStart$continuation$1
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

class GlobalCoroutineExceptionHandler : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
//        println("Coroutine exception: $exception")
//        throw ExceptionSuccessfullyProcessed()
    }
}

suspend fun testCoroutineExceptionHandler() {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val launch = scope.launch(CoroutineExceptionHandler { _, exception ->
        println("root exception handler")
        exception.printStackTrace()
    }) {
        launch(CoroutineExceptionHandler { _, exception ->
            println("A exception handler")
            exception.printStackTrace()
        }) {
            launch(CoroutineExceptionHandler { _, exception ->
                println("A-a exception handler")
                exception.printStackTrace()
            }) {
                println("A-a start")
                delay(1000)
                println("A-a end")
                throw RuntimeException()
            }
            println("A start")
            delay(3000)
            println("A end")
            throw RuntimeException()
        }
    }

    launch.join()
}

suspend fun testException() {
    val scope = CoroutineScope(Job())
    scope.launch(CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
    }) {
        launch(CoroutineExceptionHandler { _, e ->
            e.printStackTrace()
        }) {
            launch {
                delay(10)
                throw RuntimeException()
            }
            delay(10)
//            throw RuntimeException()
        }
    }.join()
}

suspend fun testCancel2() {
    val scope = CoroutineScope(Job())
    val jobA = scope.launch(CoroutineName("A")) {
        val jobChildA = launch(CoroutineName("child-A")) {
            delay(1000)
            println("xxx in child-A")
        }
        val jobChildB = launch(CoroutineName("child-B")) {
            delay(1000)
            println("xxx in child-B")
        }
        jobChildA.cancel()
    }
    val jobB = scope.launch(CoroutineName("B")) {
        delay(500)
        println("xxx in B")
    }
//    scope.cancel()
    jobA.join()
    jobB.join()
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