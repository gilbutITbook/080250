import Person.Companion.parsePerson

class Person(val firstName: String, val familyName: String) {
  companion object {
    fun String.parsePerson(): Person? {
      val names = split(" ")
      return if (names.size == 2) Person(names[0], names[1]) else null
    }
  }
}

fun main() {
  // Person.Companion 인스턴스가 암시적으로 공급됨
  println("John Doe".parsePerson()?.firstName) // John
}
