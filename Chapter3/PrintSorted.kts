fun printSorted(vararg items: Int) {
  items.sort()
  println(items.contentToString())
}

fun main() {
  printSorted(6, 2, 10, 1) // [1, 2, 6, 10]
}

main()

val numbers = intArrayOf(6, 2, 10, 1)
printSorted(*numbers)
//printSorted(numbers) // Error: passing IntArray instead of Int

fun main2() {
  val a = intArrayOf(6, 2, 10, 1)
  printSorted(*a)        // [1, 2, 6, 10]
  println(a.contentToString()) // [6, 2, 10, 1]
}

main2()
