import java.io.File

fun main() {
  File("my/nested/dir").mkdirs()
  val root = File("my")
  
  println("Dir exists: ${root.exists()}")                  // true
  println("Simple delete: ${root.delete()}")               // false
  println("Dir exists: ${root.exists()}")                  // true
  println("Recursive delete: ${root.deleteRecursively()}") // true
  println("Dir exists: ${root.exists()}")                  // false
}
