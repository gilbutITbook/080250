import java.io.File
import kotlin.io.FileWalkDirection.*

fun main() {
  File("my/dir").mkdirs()
  File("my/dir/data1.txt").also { it.writeText("One") }
  File("my/dir/data2.txt").also { it.writeText("Two") }
  
  println(File("my").walk().map { it.name }.toList())
  println(File("my").walk(TOP_DOWN).map { it.name }.toList())
  println(File("my").walk(BOTTOM_UP).map { it.name }.toList())
}
