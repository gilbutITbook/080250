class Person(val name: String, val age: Int)

fun Person.hasName(name: String) = name.equals(this.name,
                                               ignoreCase = true)

fun main() {
  val f = Person("John", 25)::hasName
  println(f("JOHN")) // true
  println(f("JAKE")) // false
}