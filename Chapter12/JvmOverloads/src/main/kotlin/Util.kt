// util.kt
fun restrictToRange(
    what: Int,
    from: Int = Int.MIN_VALUE,
    to: Int = Int.MAX_VALUE
): Int {
    return Math.max(from, Math.min(to, what))
}

@JvmOverloads
fun restrictToRange2(
    what: Int,
    from: Int = Int.MIN_VALUE,
    to: Int = Int.MAX_VALUE
): Int {
    return Math.max(from, Math.min(to, what))
}

fun main() {
    println(restrictToRange(100, 1, 10)) // 10
    println(restrictToRange(100, 1)) // 100
    println(restrictToRange(100)) // 100
    println(restrictToRange2(100, 1, 10)) // 10
    println(restrictToRange2(100, 1)) // 100
    println(restrictToRange2(100)) // 100
}
