class Address(val city: String, val street: String, val house: String)

class Person(val firstName: String, val familyName: String) {
  fun Address.post(message: String) { }
}

fun main() {
  with(Person("John", "Watson")) {
    Address("London", "Baker Street", "221b").post("Hello")
  }
}
