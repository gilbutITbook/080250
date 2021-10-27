class Address(val city: String, val street: String, val house: String)

class Person(val firstName: String, val familyName: String) {
  // Person 클래스 밖에서는 쓸 수 없음
  private fun Address.post(message: String) { }
  fun test(address: Address) = address.post("Hello")
}
