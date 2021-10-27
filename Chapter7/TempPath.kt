import kotlin.io.path.createTempDirectory
import kotlin.io.path.createTempFile

val tmpDir = kotlin.io.path.createTempDirectory(prefix = "data")
val tmpFile = kotlin.io.path.createTempFile(directory = tmpDir)

fun main() {
    println(tmpDir)
	println(tmpFile)
}