package io.nullptr.confluence.retriever.ops

import io.nullptr.confluence.retriever.ConfluenceClient
import io.nullptr.confluence.retriever.dto.Page
import io.nullptr.confluence.retriever.dto.PageSummary

class ConfluencePageOperations(private val client: ConfluenceClient) {

    object Methods {
        const val GET_PAGES = "getPages"
        const val GET_PAGE_BY_ID = "getPage"
    }

    /**
     * returns all the summaries in the space.
     */
    fun getPages(spaceKey: String, token: String? = null): List<PageSummary> {
        val response = client.httpCall<List<PageSummary>>(Methods.GET_PAGES, token, spaceKey)
        return response?.result ?: emptyList()
    }

    /**
     * returns a single Page.
     */
    fun getPage(pageId: Long, token: String? = null): Page? {
        val response = client.httpCall<Page>(Methods.GET_PAGE_BY_ID, token, pageId)
        return response?.result
    }
}