import kotlinx.coroutines.*

@Suppress("EXPERIMENTAL_API_USAGE")
fun main() {
    newSingleThreadContext("Worker").use { worker ->
        runBlocking {
            println(Thread.currentThread().name)   // main
            withContext(worker) {
                println(Thread.currentThread().name) // Worker
            }
            println(Thread.currentThread().name)   // main
        }
    }
}
