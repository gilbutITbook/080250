package foo.bar.util

fun readInt(radix: Int = 10) = readLine()!!.toInt(radix)

fun main() {
    println(readInt(8))
}

main()