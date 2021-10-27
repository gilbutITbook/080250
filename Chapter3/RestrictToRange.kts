fun restrictToRange(
  from: Int = Int.MIN_VALUE,
  to: Int = Int.MAX_VALUE,
  what: Int
): Int = Math.max(from, Math.min(to, what))

fun main() {
  println(restrictToRange(10, what = 1))
}

main()