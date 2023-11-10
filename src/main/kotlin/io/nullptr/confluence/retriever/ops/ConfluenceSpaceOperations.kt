package io.nullptr.confluence.retriever.ops

import io.nullptr.confluence.retriever.ConfluenceClient
import io.nullptr.confluence.retriever.dto.SpaceSummary

class ConfluenceSpaceOperations(private val client: ConfluenceClient) {

    object Methods {
        const val GET_SPACES = "getSpaces"
    }

    /**
     * returns all the summaries that the current user can see.
     */
    fun getSpaces(token: String? = null): List<SpaceSummary> {
        val response = client.httpCall<List<SpaceSummary>>(Methods.GET_SPACES, token)
        return response?.result ?: emptyList()
    }
}