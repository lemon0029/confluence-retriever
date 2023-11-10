package io.nullptr.confluence.retriever

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.nullptr.confluence.retriever.ops.ConfluenceSpaceOperations
import io.nullptr.confluence.retriever.rpc.ErrorRpcResponse
import io.nullptr.confluence.retriever.rpc.RpcResponse
import io.nullptr.confluence.retriever.rpc.buildJsonRemoteProcedureCallRequest
import io.nullptr.confluence.retriever.util.thisLogger
import kotlinx.coroutines.runBlocking

class ConfluenceClient(val baseUrl: String, val session: String? = null) {

    companion object {
        val httpClient = HttpClient(Apache) {
            install(ContentNegotiation) {
                json()
            }

            engine {
                followRedirects = true
                socketTimeout = 10_000
                connectTimeout = 10_000
                connectionRequestTimeout = 20_000
                customizeClient {
                    setMaxConnTotal(1000)
                    setMaxConnPerRoute(100)
                    setSSLHostnameVerifier { _, _ -> true }
                }
            }
        }
    }

    val spaceOps = ConfluenceSpaceOperations(this)

    inline fun <reified T> httpCall(method: String, vararg params: Any?): RpcResponse<T>? =
        runBlocking { httpCallAsync(method, *params) }

    suspend inline fun <reified T> httpCallAsync(method: String, vararg params: Any?): RpcResponse<T>? {
        val rpcRequest = buildJsonRemoteProcedureCallRequest(method, params.filterNotNull())

        val url = "${baseUrl}/rpc/json-rpc/confluenceservice-v2"
        val httpResponse = httpClient.post(url) {
            setBody(rpcRequest)
            contentType(ContentType.Application.Json)

            if (session != null) {
                cookie("JSESSIONID", session)
            }
        }

        try {
            val errorRpcResponse = httpResponse.body<ErrorRpcResponse>()
            handleErrorResponse(errorRpcResponse, method, params.filterNotNull())
            return null
        } catch (_: Exception) {
        }

        return httpResponse.body()
    }

    fun handleErrorResponse(response: ErrorRpcResponse, method: String, vararg params: Any) {
        val parameters = params.joinToString(", ").removeSurrounding("[", "]")
        val signature = "$method(${parameters})"
        val result = "[${response.code}] ${response.message}"
        thisLogger().error("invoke $signature failed: $result")
    }
}