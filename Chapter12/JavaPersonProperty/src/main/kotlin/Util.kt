class UtilPerson(val firstName: String, val familyName: String)

val UtilPerson.fullName
    get() = "$firstName $familyName"

fun readPerson(): UtilPerson? {
    println("Pleas input full name: ")
    val fullName = readLine() ?: return null
    val p = fullName.indexOf(' ')
    return if (p >= 0) {
        UtilPerson(fullName.substring(0, p), fullName.substring(p + 1))
    } else {
        UtilPerson(fullName, "")
    }
}
