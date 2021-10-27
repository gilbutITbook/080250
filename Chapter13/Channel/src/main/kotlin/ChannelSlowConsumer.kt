import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(Channel.CONFLATED)

        launch {
            for (n in 1..streamSize) {
                delay(100)
                val square = n*n
                println("Sending: $square")
                channel.send(square)
            }
        }

        launch {
            for (i in 1..streamSize) {
                delay(200)
                val n = channel.receive()
                println("Receiving: $n")
            }
        }
    }
}
