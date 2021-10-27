class Address(val city: String, val street: String, val house: String) {
  fun test(person: Person) {
    with(person) {
      // 암시적 디스패치와 확장 수신 객체
      post("Hello")
    }
  }
}

class Person(val firstName: String, valfamilyName: String) {
  fun Address.post(message: String) { }
}
