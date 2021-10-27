import java.io.File

fun main() {
  val file = File("data.bin")
  file.outputStream().use { it.write("Hello!".toByteArray()) }
  file.inputStream().use {
    println(String(it.readAllBytes()))
  } // Hello!
}
