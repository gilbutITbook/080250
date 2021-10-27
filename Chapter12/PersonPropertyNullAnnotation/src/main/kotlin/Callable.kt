import java.util.concurrent.Callable
import java.util.concurrent.ScheduledThreadPoolExecutor
fun main() {
    val executor = ScheduledThreadPoolExecutor(5)
    val future = executor.submit(Callable { 1 + 2 })

    println(future.get()) // 3
    executor.shutdown()
}
