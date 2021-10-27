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
  val persons = sequenceOf(
    Person("Brook", "Hudson", 25),
    Person("Silver", "Watts", 30),
    Person("Dane", "Hall", 19),
    Person("Val", "Ortiz", 28)
  )
  
  // Brook Hudson: 25
  println(persons.minWithOrNull(FULL_NAME_COMPARATOR))
  // Val Ortiz: 28
  println(persons.maxWithOrNull(FULL_NAME_COMPARATOR))
  // Dane Hall: 19
  println(persons.minWithOrNull(REVERSE_FULL_NAME_COMPARATOR))
  // Silver Watts: 30
  println(persons.maxWithOrNull(REVERSE_FULL_NAME_COMPARATOR))
}