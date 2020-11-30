import io.ktor.client.*
import io.ktor.client.engine.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.*
import kotlin.test.Test
import kotlin.test.assertTrue

class SampleTests {
    @Test
    fun testAllTeaHouses() {
        val teAppApi = TeAppApi()

        GlobalScope.async {
            println(teAppApi.allTeaHouses()[0].address)
        }
    }
}