package data

import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val icon_url: String? = null,
    val link: String = "",
    val title: String = ""
)