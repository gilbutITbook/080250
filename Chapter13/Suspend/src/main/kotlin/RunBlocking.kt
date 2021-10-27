import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch {
        delay(100)
        println("Background task: ${Thread.currentThread().name}")
    }
    runBlocking {
        println("Primary task: ${Thread.currentThread().name}")
        delay(200)
    }
}
