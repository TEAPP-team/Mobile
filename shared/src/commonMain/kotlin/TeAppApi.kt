import data.TeaHouse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.util.*

class TeAppApi() {
    @KtorExperimentalAPI
    private val client = HttpClient() {
        install(JsonFeature) {

        }
    }

    @KtorExperimentalAPI
    suspend fun allTeaHouses(): List<TeaHouse> {
        return client.get<List<TeaHouse>> {
            url("$baseUrl/api/teahouses/all")
        }
    }

    companion object {
        private const val baseUrl = "https://teappp.herokuapp.com"
    }
}


