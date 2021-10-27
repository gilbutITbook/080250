fun printSorted(vararg items: Int) { } // 1

fun printSorted(a: Int, b: Int, c: Int) { } // 2

fun main() {
  printSorted(1, 2, 3) // 2번 함수가 가변인자 함수가 아니므로 2번을 선택
  printSorted(1, 2)    // 적용할 수 있는 함수가 1번밖에 없으므로 1번을 선택
}
