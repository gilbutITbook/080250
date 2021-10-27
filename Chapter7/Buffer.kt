import java.io.File

fun main() {
  val file = File("data.txt")
  
  file.bufferedWriter().use { it.write("Hello!") }
  file.bufferedReader().use { println(it.readLine()) } // Hello!
}
