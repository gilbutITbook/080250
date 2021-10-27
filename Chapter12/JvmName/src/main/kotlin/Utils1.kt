@file:JvmMultifileClass
@file:JvmName("MyUtils")

val Person.fullName
    get() = "$firstName $familyName"
