fun printSorted(vararg items: Int, prefix: String = "") { }

fun main() {
  printSorted(6, 2, 10, 1, "!")          // Error: "" is taken as part of vararg
  printSorted(6, 2, 10, 1, prefix = "!") // 정상
}

main()