import kotlin.concurrent.thread

fun main() {
    println("Starting a thread...")

    thread(name = "Worker", isDaemon = true) {
        for (i in 1..5) {
            println("${Thread.currentThread().name}: $i")
            Thread.sleep(150)
        }
    }

    Thread.sleep(500)
    println("Shutting down...")
}
