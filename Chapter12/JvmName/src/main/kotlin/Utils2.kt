@file:JvmMultifileClass
@file:JvmName("MyUtils")

fun readPerson(): Person? {
    println("please input full name: ")
    val fullName = readLine() ?: return null
    val p = fullName.indexOf(' ')

    return if (p >= 0) {
        Person(fullName.substring(0, p), fullName.substring(p + 1))
    } else {
        Person(fullName, "")
    }
}
