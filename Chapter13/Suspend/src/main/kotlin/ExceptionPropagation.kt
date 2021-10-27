import kotlinx.coroutines.*

fun main() {
    runBlocking {
        launch {
            throw Exception("Error in task A")
            println("Task A completed")
        }

        launch {
            delay(1000)
            println("Task B completed")
        }

        println("Root")
    }
}
