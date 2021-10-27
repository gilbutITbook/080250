import kotlin.reflect.KFunction2

fun combine(n: Int, s: String) = "$s$n"

fun main() {
  val f: KFunction2<Int, String, String> = ::combine
  println(f(1, "2")) // 21
}
