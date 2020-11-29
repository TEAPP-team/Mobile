import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.request.*
import kotlin.collections.get

class TeAppApi(private val engine: HttpClientEngine) {
    private val client = HttpClient()

    suspend fun allTeaHouses(): String {
        return client.get<String> {
            url("$baseUrl/api/teahouses/all")
        }
    }

    companion object {
        private const val baseUrl = "https://teappp.herokuapp.com"
    }

    data class TeaHouse(val title: String, val address: String)
}