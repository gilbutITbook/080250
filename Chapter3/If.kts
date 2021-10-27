fun max(a: Int, b: Int): Int {
  if (a > b) return a
  else return b
}

fun main(args: Array<String>) {
  if (args.isNotEmpty()) {
    val message = "Hello, ${args[0]}"
    println(message)
  } else {
    println()
  }
}

main(arrayOf("World"))