fun interface SomeCallback {
    fun execute(arg: Any): Unit
}

fun callWithString(arg: String, callback: SomeCallback) = callback.execute(arg)

fun main() {
    callWithString("World!"){println("Hello,$it")}  // Hello, World!
}
