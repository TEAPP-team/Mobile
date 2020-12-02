package data

import kotlinx.serialization.Serializable

@Serializable
data class Weekend(
    val from: String = "",
    val to: String = ""
)