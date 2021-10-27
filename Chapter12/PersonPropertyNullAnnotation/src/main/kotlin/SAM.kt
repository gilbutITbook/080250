import java.util.concurrent.ScheduledThreadPoolExecutor

fun main() {
    val executor = ScheduledThreadPoolExecutor(5)
    // 암시적으로 Runnable으로 변환됨
    val future = executor.submit { 1 + 2 }

    println(future.get()) // null
    executor.shutdown()
}
