import java.io.File

fun main() {
  val file = File("data.txt")
  
  file.writeText("One\nTwo\nThree")
  println(file.useLines { lines ->lines.count { it.length> 3 } }) // 1
}
