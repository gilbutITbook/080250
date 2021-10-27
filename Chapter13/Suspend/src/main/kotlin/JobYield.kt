import kotlinx.coroutines.*

suspend fun main() {
    val squarePrinter = GlobalScope.launch(Dispatchers.Default) {
        var i = 1
        while (true) {
            yield()
            println(i++)
        }
    }
    delay(100) // 자식 잡이 어느 정도 실행될 시간을 준다
    squarePrinter.cancel()
}
