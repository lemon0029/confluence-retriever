package io.nullptr.confluence.retriever.rpc

import java.util.concurrent.atomic.AtomicLong

object RpcRequestIdentityGenerator {
    private val id = AtomicLong(10000)

    fun generate(): Long {
        return id.getAndIncrement()
    }
}
