package com.kevin

import kotlinx.atomicfu.atomic

/**
 * Created by: kevin
 * Date: 2023-05-31
 */
class TestAtomic {
    private val _queue = atomic<Any?>(null)

}