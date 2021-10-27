import kotlin.reflect.KProperty

class CachedProperty<in R, out T : Any>(val initializer: R.() -> T) {
  private val cachedValues  = HashMap<R, T>()
  
  operator fun getValue(receiver: R, property: KProperty<*>): T {
    return cachedValues.getOrPut(receiver) { receiver.initializer() }
  }
}

fun <R, T : Any> cached(initializer: R.() -> T) = CachedProperty(initializer)

class Person(val firstName: String, val familyName: String)

val Person.fullName: String by cached { "$firstName $familyName" }

fun main() {
  val johnDoe = Person("John", "Doe")
  val harrySmith = Person("Harry", "Smith")
  
  // johnDoe  수신 객체에 최초 접근. 값을 계산해 캐시에 담음
  println(johnDoe.fullName)  // John Doe
  
  // harrySmith 수신 객체에 최초 접근. 값을 계산해 캐시에 담음
  println(harrySmith.fullName) // Harry Smith
  
  // johnDoe 수신 객체에 재접근. 캐시에서 값을 읽음
  println(johnDoe.fullName)  // John Doe
  
  // harrySmith 수신 객체에 재접근. 캐시에서 값을 읽음
  println(harrySmith.fullName) // Harry Smith
}
