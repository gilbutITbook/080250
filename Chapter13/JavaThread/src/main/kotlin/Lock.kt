import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

class Counter2 {
    private var value = 0
    private val lock = ReentrantLock()

    fun addAndPrint(value: Int) {
        lock.withLock {
            this.value += value
            println(value)
        }
    }
}

fun main() {
    val counter = Counter2()
    for (i in 1..5) {
        thread(isDaemon = false) { counter.addAndPrint(i) }
    }
}

