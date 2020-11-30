package data

import kotlinx.serialization.Serializable

@Serializable
data class WorkTime(val from: String, val to: String)