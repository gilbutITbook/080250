import java.io.File

fun main() {
  val file = File("data.txt")
  
  file.writeText("One\nTwo\nThree")
  file.forEachLine { print("/$it") } // /One/Two/Three
}
