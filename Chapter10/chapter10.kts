import kotlin.reflect.KClass
/*
import org.junit.Test

class MyTestCase {
  @Test
  fun testOnePlusOne() {
    assert(1 + 1 == 2)
  }
}
*/

//val s = @Suppress("UNCHECKED_CAST") objects as List<String>  // objects가 없어서 컴파일은 안됨

@[Synchronized Strictfp] // @Synchronized @Strictfp와 같은 역할을 함
fun main() { }

annotation class MyAnnotation
class A @MyAnnotation constructor ()

annotation class MyAnnotation2
@MyAnnotation2 fun annotatedFun() { }

annotation class MyAnnotation3 {
  companion object {
    val text = "???"
  }
}


annotation class MyAnnotation4(val text: String)

@MyAnnotation4("Some useful info") fun annotatedFun2() { }

annotation class Dependency(val arg:String, val componentNames:String="Core")
annotation class Component(val name: String = "Core")

@Component("I/O")
class IO

@Component("Log")
@Dependency("I/O")
class Logger

@Component
@Dependency("I/O", "Log")
class Main

//annotation class Component2(val name: String = "Core")
//val ioComponent = Component2("IO") // error: annotation class cannot be instantiated

annotation class Dependency2(vararg val componentNames: String)

annotation class Component3(
  val name: String = "Core",
  val dependency: Dependency2 = Dependency2()
)

@Component3("I/O")
class IO2

@Component3("Log", Dependency2("I/O"))
class Logger2

@Component3(dependency = Dependency2("I/O", "Log"))
class Main2

annotation class Dependency3(val componentNames: Array<String>)

annotation class Component4(
  val name: String = "Core",
  val dependency: Dependency3 = Dependency3(arrayOf())
)

@Component4(dependency = Dependency3(arrayOf("I/O", "Log")))
class Main3

annotation class Dependency4(val componentNames: Array<String>)

annotation class Component5(
  val name: String = "Core",
  val dependency: Dependency4 = Dependency4(arrayOf())
)

@Component5(dependency = Dependency4(["I/O", "Log"]))
class Main4

//import kotlin.reflect.KClass // 파일 맨 앞에서 임포트

annotation class Dependency5(vararg val componentClasses: KClass<*>)

annotation class Component6(
  val name: String = "Core",
  val dependency: Dependency5 = Dependency5()
)

@Component6("I/O")
class IO3

@Component6("Log", Dependency5(IO3::class))
class Logger3

@Component6(dependency = Dependency5(IO3::class, Logger3::class))
class Main5

class Person(val name: String)

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class AA

@Target(AnnotationTarget.PROPERTY_GETTER)
annotation class BB

class Person2(@get:AA val name: String)


class Person3(@get:[AA BB] val name: String)

class Person4(@get:AA @get:BB val name: String)

class Person5(val firstName: String, val familyName: String)

annotation class AAA

fun @receiver:AAA Person5.fullName() = "$firstName $familyName"

//error: expression annotations with retention other than SOURCE are prohibited
//@Target(AnnotationTarget.EXPRESSION)
//annotation class NeedToRefactor

@Target(AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class NeedToRefactor2 // Ok

@Repeatable
@Retention(AnnotationRetention.SOURCE)
annotation class Author(val name: String)

@Author("John")
@Author("Harry")
class Services

//@Deprecated("Deprecated")
//@Deprecated("Even more deprecated") // error: this annotation is not repeatable
//class OldClass

val strings = listOf<Any>("1", "2", "3")
val numbers = listOf<Any>(1, 2, 3)

// 경고 표시되지 않음
val s = @Suppress("UNCHECKED_CAST") (strings as List<String>)[0]

// warning: unchecked cast: List<Any> to List<Number>
val n = (numbers as List<Number>)[1]

@Suppress("UNCHECKED_CAST")
fun main2() {
  val strings = listOf<Any>("1", "2", "3")
  val numbers = listOf<Any>(1, 2, 3)
  val s = (strings as List<String>)[0] // 경고 표시되지 않음
  val n = (numbers as List<Number>)[1] // 경고 표시되지 않음
  
  println(s + n) // 12
}

main2()

val numbers2 = listOf<Any>(1, 2, 3)
fun foo(l:List<Number>) = l[0]
println(foo(numbers2 as List<Number>))


@Deprecated(
  "Use readInt() instead", // 메시지
  ReplaceWith("readInt()") // 대안
)
fun readNum() = readLine()!!.toInt()

fun readInt(radix: Int=10) = readLine()!!.toInt(radix)

fun main3() {
    println("Input a number: ")
    val a = readNum()
	println("Input a number: ")
	val b = readNum()
}

main3()

// 리플렉션 부분은 chapter10_2.kts로