class Person(val firstName: String,
             val familyName: String,
             val age: Int) {
  override fun toString() = "$firstName $familyName: $age"
}

val Person.fullName get() = "$firstName $familyName"
val Person.reverseFullName get() = "$familyName $firstName"

val FULL_NAME_COMPARATOR = Comparator<Person>{ p1, p2 ->
  p1.fullName.compareTo(p2.fullName)
}
val REVERSE_FULL_NAME_COMPARATOR = Comparator<Person>{ p1, p2 ->
  p1.reverseFullName.compareTo(p2.reverseFullName)
}

fun main() {
  val persons = listOf(
    Person("Brook", "Hudson", 25),
    Person("Silver", "Watts", 30),
    Person("Dane", "Hall", 19),
    Person("Val", "Ortiz", 28)
  )
  
  println(persons.sortedWith(FULL_NAME_COMPARATOR))
  println(persons.sortedWith(FULL_NAME_COMPARATOR))
  println(persons.sortedWith(REVERSE_FULL_NAME_COMPARATOR))
  println(persons.sortedWith(REVERSE_FULL_NAME_COMPARATOR))
  println(persons.sortedBy { it.age })
  println(persons.sortedByDescending { it.age })
}
