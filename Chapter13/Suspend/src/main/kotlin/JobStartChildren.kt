import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job = coroutineContext[Job.Key]!!

        launch { println("This is task A") }
        launch { println("This is task B") }

        // 2 children running
        println("${job.children.count()} children running")
    }
}
