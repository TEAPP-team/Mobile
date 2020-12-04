package data

import kotlinx.serialization.Serializable

@Serializable
data class TeaHouse(
    val address: String = "",
    val coordinates: Coordinates = Coordinates(),
    val id: Int = 0,
    val links: List<Link>? = listOf(),
    val phone: String = "",
    val site: String? = "",
    val title: String = "",
    val workTime: WorkTimeX = WorkTimeX()
)