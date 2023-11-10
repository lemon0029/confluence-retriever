package io.nullptr.confluence.retriever.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageSummary(
    val id: Long,

    @SerialName("space")
    val spaceKey: String,

    val title: String,
    val url: String,
    val permissions: Int,
    val parentId: Long,
    val version: Int
)