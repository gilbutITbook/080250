import java.io.*

fun main() {
  FileWriter("data.txt").use { it.write("One\nTwo\nThree") }
  
  // One, Two, Three
  FileReader("data.txt").useLines { println(it.joinToString()) }
  
  // One/Two/Three
  FileReader("data.txt").forEachLine { print("$it/") }
}
