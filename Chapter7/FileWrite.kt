import java.io.*

fun main() {
  FileWriter("data.txt").use { it.write("Hello") }
  
  val writer = StringWriter()
  FileReader("data.txt").use { it.copyTo(writer) }
  println(writer.buffer)            // Hello
  
  val output = ByteArrayOutputStream()
  FileInputStream("data.txt").use { it.copyTo(output) }
  println(output.toString("UTF-8")) // Hello
}
