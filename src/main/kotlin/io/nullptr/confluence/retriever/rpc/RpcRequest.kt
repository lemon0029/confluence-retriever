package io.nullptr.confluence.retriever.rpc

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RpcRequest(
    @SerialName("method")
    val method: String,

    @SerialName("params")
    val params: List<String>,

    @SerialName("id")
    val identity: Long,

    @SerialName("jsonrpc")
    val version: String = "2.0"
)

fun buildJsonRemoteProcedureCallRequest(method: String, vararg params: Any?): RpcRequest {
    val requestIdentity = RpcRequestIdentityGenerator.generate()
    return RpcRequest(method, params.filterNotNull().map { it.toString() }, requestIdentity)
}

