import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val parentJob = launch {
            println("Parent started")

            launch {
                println("Child 1 started")
                delay(500)
                println("Child 1 completed")
            }

            launch {
                println("Child 2 started")
                delay(500)
                println("Child 2 completed")
            }

            delay(500)
            println("Parent completed")
        }

        delay(100)
        parentJob.cancel()
    }
}
