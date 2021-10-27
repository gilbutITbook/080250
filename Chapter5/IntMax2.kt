fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
  var result = numbers.firstOrNull()
      ?: throw IllegalArgumentException("Empty array")
      
  for (i in 1..numbers.lastIndex) result = op(result, numbers[i])
  
  return result
}

fun Int.max(other: Int) = if (this > other) this else other

fun main() {
  println(aggregate(intArrayOf(1, 2, 3, 4), Int::plus)) // 10
  println(aggregate(intArrayOf(1, 2, 3, 4), Int::max))  // 4
}
