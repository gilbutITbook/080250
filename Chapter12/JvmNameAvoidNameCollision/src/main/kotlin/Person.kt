class Person(var firstName: String, val familyName: String)

val Person.fullName
    get() = "$firstName $familyName"


// @JvmName("getFullNameFamilyFirst")을 안붙이면 메서드 시그니처가 일치해서 컴파일 오류가 난다.
// error: Platform declaration clash: The following declarations have the same JVM signature (getFullName(LPerson;)Ljava/lang/String;):
@JvmName("getFullNameFamilyFirst")
fun getFullName(person: Person): String {
    return "${person.familyName}, ${person.firstName}"
}

val Person.fullName2
    @JvmName("getFullNameFamilyLast")
    get() = "$firstName $familyName"

@get:JvmName("getFullNameFamilyLast2")
val Person.fullName3
   get() = "$firstName $familyName"
