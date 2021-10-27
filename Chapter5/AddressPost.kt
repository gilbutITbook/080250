class Address(val city: String, val street: String, val house: String) {
  fun test(person: Person) {
    person.post("Hello") // Error: method post() is not defined
  }
}

class Person(val firstName: String, val familyName: String) {
  fun Address.post(message: String) { }
}
