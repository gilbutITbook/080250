import kotlin.concurrent.thread

class Counter {
    private var value = 0
    @Synchronized fun addAndPrint(value: Int) {
        this.value += value
        println(value)
    }
}

fun main() {
    val counter = Counter()
    for (i in 1..5) {
        thread(isDaemon = false) { counter.addAndPrint(i) }
    }
}
