import java.io.File

fun main() {
  val source = File("data.txt").also { it.writeText("One") }
  val target = File("dataNew.txt").also { it.writeText("Two") }
  source.copyTo(target, overwrite = true)
  println(target.readText()) // One
}
