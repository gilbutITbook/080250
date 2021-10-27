import kotlin.reflect.KCallable

val simpleVal = 1
val Int.extVal get() = this

class A {
  val Int.memberExtVal get() = this
}

fun main() {
  fun printParams(callable: KCallable<*>) {
    println(
      callable.parameters.joinToString(prefix = "[", postfix = "]") {
        it.type.toString()
      }
    )
  }
  
  // []
  printParams(::simpleVal)
  
  // [kotlin.Int]
  printParams(Int::extVal)
  
  // [A, kotlin.Int]
  printParams(A::class.members.first { it.name == "memberExtVal" })
}
