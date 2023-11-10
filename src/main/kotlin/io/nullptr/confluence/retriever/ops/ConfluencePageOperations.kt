package io.nullptr.confluence.retriever.ops

import io.nullptr.confluence.retriever.ConfluenceClient
import io.nullptr.confluence.retriever.dto.PageSummary

class ConfluencePageOperations(private val client: ConfluenceClient) {

    object Methods {
        const val GET_PAGES = "getPages"
    }

    /**
     * returns all the summaries in the space.
     */
    fun getPages(spaceKey: String, token: String? = null): List<PageSummary> {
        val response = client.httpCall<List<PageSummary>>(Methods.GET_PAGES, token, spaceKey)
        return response?.result ?: emptyList()
    }
}