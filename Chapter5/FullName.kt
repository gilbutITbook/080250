class Person(val firstName: String, val familyName: String) {
  fun fullName() = "$firstName $familyName"
}

fun Person.fullName() = "$familyName $firstName"

fun main() {
  println(Person("John", "Doe").fullName()) // ???
}
