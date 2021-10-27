import java.io.File

fun main() {
  val file = File("data.txt")
  
  file.writeText("One")
  println(file.readText()) // One
  
  file.appendText("\nTwo")
  println(file.readLines()) // [One, Two]
  
  file.writeText("Three")
  println(file.readLines()) // [Three]
}
