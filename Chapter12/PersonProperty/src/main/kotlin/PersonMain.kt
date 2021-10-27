import java.math.BigInteger
import java.util.*

fun main() {
    val person = Person("John", 25)
    person.name = "Harry"
    person.age = 30

    println("${person.name}, ${person.age}") // Harry, 30
    println("${person.name}, ${person.age} employed: ${person.isEmployed}") // false

    // 플랫폼타입 예시
    val person2 = Person(null, 25)
    println(person2.name.length)

    // BigInteger! 반환 타입
    fun Int.toBigInt() = BigInteger.valueOf(toLong())

    val num = 123.toBigInt() // BigInteger! 타입
}
