package io.nullptr.confluence.retriever.dto

import kotlinx.serialization.Serializable

@Serializable
data class SpaceSummary(
    val key: String,
    val name: String,
    val url: String,
    val type: SpaceType
)