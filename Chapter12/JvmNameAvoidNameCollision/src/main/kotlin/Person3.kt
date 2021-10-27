class Person3(val firstName: String, val familyName: String) {
    @JvmName("visit")
    fun goto(person: Person3) {
        println("$this is visiting $person")
    }

    override fun toString() = "$firstName $familyName"
}
