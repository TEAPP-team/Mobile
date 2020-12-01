import kotlinx.coroutines.*
import kotlin.test.Test

class SampleTests {
    @Test
    fun testAllTeaHouses() {
        val teAppApi = TeappApi()

        GlobalScope.async {
            println(teAppApi.allTeaHouses()[0].address)
        }
    }
}