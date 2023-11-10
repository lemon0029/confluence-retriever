package io.nullptr.confluence.retriever.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerInformation(
    val majorVersion: Int,
    val minorVersion: Int,
    val patchLevel: Int,
    val developmentBuild: Boolean,
    val buildId: String,
    val baseUrl: String
)