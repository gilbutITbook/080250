fun aggregate(numbers: IntArray, op: Int.(Int) -> Int): Int {
  var result = numbers.firstOrNull()
      ?: throw IllegalArgumentException("Empty array")
      
  for (i in 1..numbers.lastIndex) result = result.op(numbers[i])
  
  return result
}

fun Int.max(other: Int) = if (this > other) this else other

fun main() {
  val numbers = intArrayOf(1, 2, 3, 4)
  println(aggregate(numbers, Int::plus)) // 10
  println(aggregate(numbers, Int::max))  // 4
}

