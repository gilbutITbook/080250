import kotlinx.coroutines.channels.*
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val channel = produce {
            for (n in 1..5) {
                val square = n*n
                println("Sending: $square")
                send(square)
            }
        }

        launch {
            channel.consumeEach { println("Receiving: $it") }
        }
    }
}
