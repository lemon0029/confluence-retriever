package io.nullptr.confluence.retriever.dto

import kotlinx.serialization.SerialName

enum class SpaceType {
    @SerialName("global")
    GLOBAL,

    @SerialName("personal")
    PERSONAL
}