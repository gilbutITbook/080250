fun main() {
  fun readInt() = readLine()!!.toInt()
  println(readInt() + readInt())
}

fun readIntPair() = intArrayOf(readInt(), readInt()) // error: unresolved reference: readInt

main()