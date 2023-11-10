package io.nullptr.confluence.retriever.rpc

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RpcResponse<T>(
    @SerialName("result")
    val result: T,

    @SerialName("id")
    val identity: Long,

    @SerialName("jsonrpc")
    val version: String
)

@Serializable
data class ErrorRpcResponse(
    @SerialName("code")
    val code: Int,

    @SerialName("message")
    val message: String,

    @SerialName("data")
    val data: String?
)
