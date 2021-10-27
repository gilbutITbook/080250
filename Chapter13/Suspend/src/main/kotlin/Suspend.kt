import kotlinx.coroutines.delay

class Suspend {
    suspend fun foo() {
        println("Task started")
        delay(100)
        println("Task finished")
    }
}

suspend fun main() {
    println("Task started")
    delay(100)
    println("Task finished")
}