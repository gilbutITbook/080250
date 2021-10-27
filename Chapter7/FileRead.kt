import java.io.*

fun main() {
  FileWriter("data.txt").use { it.write("One\nTwo\nThree") }
  
  // One
  FileReader("data.txt").buffered().use { println(it.readLine()) }
  
  // One Two Three
  FileReader("data.txt").use { println(it.readText().replace('\n', ' ')) }
  
  // [One, Two, Three]
  println(FileReader("data.txt").readLines())
}
