import kotlin.reflect.jvm.isAccessible
 
class Person(val firstName: String, val familyName: String) {
  val fullName by lazy { "$firstName $familyName" }
}

fun main() {
  val person = Person("John", "Doe")
  
  // KProperty0: 모든 수신 객체가 엮여 있음
  println(
    person::fullName
      .apply { isAccessible = true }
      .getDelegate()!!::class.qualifiedName
  ) // kotlin.SynchronizedLazyImpl
  
  // KProperty1: 수신 객체가 하나 있음
  println(
    Person::fullName
      .apply { isAccessible = true }
      .getDelegate(person)!!::class.qualifiedName
  ) // kotlin.SynchronizedLazyImpl
}
