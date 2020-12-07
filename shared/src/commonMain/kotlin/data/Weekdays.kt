package data

import kotlinx.serialization.Serializable

@Serializable
data class Weekdays(
    val from: String = "",
    val to: String = ""
)