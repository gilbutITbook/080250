import java.io.File

fun main() {
  val file = File("data.bin")
  var sum = 0
  
  file.forEachBlock { buffer, bytesRead ->
    (0 until bytesRead).forEach { sum += buffer[it] }
  }
  println(sum)
}
