import kotlin.concurrent.timer

fun main() {
    println("Starting a thread...")
    var counter = 0

    timer(period = 150, name = "Worker", daemon = true) {
        println("${Thread.currentThread().name}: ${++counter}")
    }

    Thread.sleep(500)
    println("Shutting down...")
}
