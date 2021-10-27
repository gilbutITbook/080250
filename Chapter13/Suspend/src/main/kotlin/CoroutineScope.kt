import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Custom scope start")

        coroutineScope {
            launch {
                delay(100)
                println("Task 1 finished")
            }

            launch {
                delay(100)
                println("Task 2 finished")
            }
        }

        println("Custom scope end")
    }
}
