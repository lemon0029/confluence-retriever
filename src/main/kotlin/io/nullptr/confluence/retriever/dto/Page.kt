package io.nullptr.confluence.retriever.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page(
    val id: Long,

    @SerialName("space")
    val spaceKey: String,

    val title: String,
    val url: String,
    val permissions: Int,
    val parentId: Long,
    val version: Int,

    val created: Long,
    val modified: Long,

    val creator: String,
    val modifier: String,
    val content: String,
    val contentStatus: String,

    val homePage: Boolean,
    val current: Boolean
)