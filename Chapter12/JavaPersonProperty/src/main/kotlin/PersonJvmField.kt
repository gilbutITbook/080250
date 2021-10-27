class PersonJvmField(@JvmField var name: String, @JvmField val age: Int)

/* 뻔하지 않은 프로퍼티의 경우 JvmField를 붙일 수 없음 */
/*
class PersonJvmField2(val firstName: String, val familyName: String) {
    // Error: This annotation is not applicable to target 'member property without backing field or delegate'
    @JvmField val fullName get() = "$firstName $familyName"
}
*/

/* 파이널이 아닌 프로퍼티의 경우 JvmField를 붙일 수 없음 */
/*
open class PersonJvmField3(val firstName: String, val familyName: String) {
    // Error: JvmField can only be applied to final property
    @JvmField open var description: String = "hello"
}
*/