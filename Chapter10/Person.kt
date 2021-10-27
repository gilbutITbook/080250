class Person(val firstName: String, val familyName: String) {
  fun fullName(familyFirst: Boolean): String = if (familyFirst) {
      "$familyName $firstName"
    } else {
      "$firstName $familyName"
    }
}

fun main() {
  val personClass = Class.forName("Person").kotlin
  val person = personClass.constructors.first().call("John", "Doe")
  val fullNameFun = personClass.members.first { it.name == "fullName" }
  
  println(fullNameFun.call(person, false)) // John Doe
}
