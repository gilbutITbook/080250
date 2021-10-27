import kotlin.concurrent.thread

fun main() {
    var counter = 0
    val lock = Any()

    for (i in 1..5) {
        // 앞의 예제에서 본 스레드를 생성하는 부분
        thread(isDaemon = false) {
            synchronized(lock) {
                counter += i
                println(counter)
            }
        }
    }

    val currentCounter = synchronized(lock) { counter }
    println("Current counter: $currentCounter")
}
