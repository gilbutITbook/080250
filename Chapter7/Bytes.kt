import java.io.File

fun main() {
  val file = File("data.bin")
  
  file.writeBytes(byteArrayOf(1, 2, 3))
  println(file.readBytes().contentToString()) // [1, 2, 3]
  
  file.appendBytes(byteArrayOf(4, 5))
  println(file.readBytes().contentToString()) // [1, 2, 3, 4, 5]
  
  file.writeBytes(byteArrayOf(6, 7))
  println(file.readBytes().contentToString()) // [6, 7]
}
