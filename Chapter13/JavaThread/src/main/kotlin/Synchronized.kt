import kotlin.concurrent.thread

fun main() {
    var counter = 0
    val lock = Any()

    for (i in 1..5) {
        thread(isDaemon = false) {
            synchronized(lock) {
                counter += i
                println(counter)
            }
        }
    }
}
