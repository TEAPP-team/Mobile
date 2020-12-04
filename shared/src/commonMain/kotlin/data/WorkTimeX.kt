package data

import kotlinx.serialization.Serializable

@Serializable
data class WorkTimeX(
    val weekdays: Weekdays= Weekdays(),
    val weekend: Weekend=Weekend()
)