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
            channel.close()
        }
        launch {
            for (n in channel) {
                println("Receiving: $n")
                delay(200)
            }
        }
    }
}
