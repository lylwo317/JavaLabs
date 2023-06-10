package com.kevin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

/**
 * Created by: kevin
 * Date: 2023-06-13
 */
class GetToken {
    suspend fun getToken(): String {
        // do something too long
        return "Token"
    }

    fun getTokenFuture(): CompletableFuture<String> {
        return CoroutineScope(Dispatchers.IO)
            .future{getToken()}
    }
}