operator fun <T> ((T) -> Boolean).not(): (T) -> Boolean = { !this(it) }

fun isShort(s: String) = s.length <= 4
fun String.isUpperCase() = all { it.isUpperCase() }

fun main() {
    val data = listOf("abc", "abcde", "ABCDE", "aBcD", "ab")
    
    println(data.count(::isShort)) // 3
    println(data.count(!::isShort)) // 2
    println(data.count(String::isUpperCase)) // 1
    println(data.count(!String::isUpperCase)) // 4
}
