import kotlinx.coroutines.*

private fun CoroutineScope.showName() {
    println("Current coroutine: ${coroutineContext[CoroutineName]?.name}")
}

fun main() {
    runBlocking {
        showName() // Current coroutine: null
        launch(coroutineContext + CoroutineName("Worker")) {
            showName() // Current coroutine: Worker
        }
    }
}
