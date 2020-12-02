import data.TeaHouse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

class TeappApi {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "teappp.herokuapp.com"
            }
        }
    }

    suspend fun allTeaHouses() = client.get<List<TeaHouse>> {
        url("/api/teahouses/all")
        accept(ContentType.Application.Json)
    }
}
