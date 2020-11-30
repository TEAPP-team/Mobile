package data

import kotlinx.serialization.Serializable

@Serializable
data class TeaHouse(
    val title: String,
    val address: String,
    val coordinates: Coordinate,
    val workTime: String,
    val phone: String,
    val site: String,
    val links: List<Link>,
    val id: Int
)