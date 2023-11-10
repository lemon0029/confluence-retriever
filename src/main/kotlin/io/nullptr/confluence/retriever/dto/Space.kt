package io.nullptr.confluence.retriever.dto

import kotlinx.serialization.Serializable

@Serializable
data class Space(
    val key: String,
    val name: String,
    val url: String,
    val type: SpaceType,

    val description: String?,
    val spaceGroup: String?,
    val homePage: Long
)