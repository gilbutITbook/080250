import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.*
import kotlin.random.Random

fun main() {
    runBlocking {
        val streamSize = 5
        val channel = Channel<Int>(2)

        launch {
            for (n in 1..streamSize) {
                val square = n*n
                println("Sending: $square")
                channel.send(square)
            }
            channel.close()
        }

        for (i in 1..3) {
            launch {
                for (n in channel) {
                    println("Receiving by consumer #$i: $n")
                    delay(Random.nextLong(100))
                }
            }
        }
    }
}
