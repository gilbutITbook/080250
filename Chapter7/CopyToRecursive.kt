import java.io.File

fun main() {
  File("old/dir").mkdirs()
  File("old/dir/data1.txt").also { it.writeText("One") }
  File("old/dir/data2.txt").also { it.writeText("Two") }
  
  File("old").copyRecursively(File("new"))
  
  println(File("new/dir/data1.txt").readText()) // One
  println(File("new/dir/data2.txt").readText()) // Two
}
