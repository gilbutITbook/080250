import kotlinx.coroutines.*
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.atomic.AtomicInteger

fun main() {
    val id = AtomicInteger(0)

    val executor = ScheduledThreadPoolExecutor(5) { runnable ->
        Thread(
            runnable,
            "WorkerThread-${id.incrementAndGet()}"
        ).also { it.isDaemon = true }
    }

    executor.asCoroutineDispatcher().use { dispatcher ->
        runBlocking {
            for (i in 1..3) {
                launch(dispatcher) {
                    println(Thread.currentThread().name)
                    delay(1000)
                }
            }
        }
    }
}
