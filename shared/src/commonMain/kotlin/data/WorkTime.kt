package data

import kotlinx.serialization.Serializable

@Serializable
data class WorkTime(
    val weekdays: Weekdays = Weekdays(),
    val weekend: Weekend = Weekend()
)