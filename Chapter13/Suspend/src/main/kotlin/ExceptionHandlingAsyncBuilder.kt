import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val deferredA = async {
            throw Exception("Error in task A")
            println("Task A completed")
        }
        val deferredB = async {
            println("Task B completed")
        }
        deferredA.await()
        deferredB.await()
        println("Root")
    }
}
