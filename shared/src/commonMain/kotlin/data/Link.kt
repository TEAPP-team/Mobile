package data

import kotlinx.serialization.Serializable

@Serializable
data class Link(val title: String, val link: String, val icon_url: String?)