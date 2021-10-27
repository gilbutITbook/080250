import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun loadData() = File("data.txt").readLines()
