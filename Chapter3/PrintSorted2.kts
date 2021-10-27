fun printSorted(prefix: String = "", vararg items: Int) { }

fun main() {
  //printSorted(6, 2, 10, 1) // Error: 6 is taken as value of prefix
  printSorted(items = *intArrayOf(6, 2, 10, 1)) // 정상
}

main()