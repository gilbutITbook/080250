// 앞에서 정의했던 !와 isUpperCase 확장함수 필요함
operator fun <T> ((T) -> Boolean).not(): (T) -> Boolean = { !this(it) }
fun String.isUpperCase() = all { it.isUpperCase() }

infix fun <T> ((T) -> Boolean).and(
  other: (T) -> Boolean
): (T) -> Boolean {
  return { this(it) && other(it) }
}

infix fun <T> ((T) -> Boolean).or(
  other: (T) -> Boolean
): (T) -> Boolean {
  return { this(it) || other(it) }
}

fun isShort(s: String) = s.length <= 4

fun main() {
  val data = listOf("abc", "abcde", "ABCDE", "aBcD", "ab")
  
  println(data.count(::isShort and String::isUpperCase)) // 0
  println(data.count(::isShort or String::isUpperCase)) // 4
  println(data.count(!::isShort or String::isUpperCase)) // 2
  println(data.count(!(::isShort and String::isUpperCase))) // 5
}
