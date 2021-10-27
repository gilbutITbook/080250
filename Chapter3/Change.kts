fun change(vararg items: IntArray) {
  items[0][0] = 100
}

fun main() {
  val a = intArrayOf(1, 2, 3)
  val b = intArrayOf(4, 5, 6)
  change(a, b)
  println(a.contentToString()) // [100, 2, 3]
  println(b.contentToString()) // [4, 5, 6]
}

main()