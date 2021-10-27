import kotlinx.coroutines.*

fun main() {
    val handler = CoroutineExceptionHandler{ _, exception ->
        println("Caught $exception")
    }

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
