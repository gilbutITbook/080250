import java.io.File

fun main() {
  val source = File("data.txt")
  source.writeText("Hello")
  
  val target = source.copyTo(File("dataNew.txt"))
  println(target.readText()) // Hello
}
