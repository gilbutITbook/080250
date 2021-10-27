fun main() {
  val a = IntArray(10) { it*it } // 0, 1, 4, 9, 16, …
  var sum = 0
  
  for (x in a) {
    sum += x
  }
  
  println("Sum: $sum") // Sum: 285
}

main()