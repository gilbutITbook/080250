fun increment(a: IntArray): Int {
  return ++a[0]
}

fun main() {
  val a = intArrayOf(1, 2, 3)
  println(increment(a)) // 2
  println(a.contentToString()) // [2, 2, 3]
}

main()