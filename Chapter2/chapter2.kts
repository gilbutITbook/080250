/*
여러 줄 주석
/* 주석 안에 내포된 주석 */
*/
println("Hello") // 한 줄짜리 주석

val timeInSeconds = 15

fun main() {
    val a = readLine()!!.toInt()
    val b = readLine()!!.toInt()
    println(a + b)
}

// var text = "Hello"; // 자동으로 text의 타입을 String으로 추론한다

val n: Int = 100

val text: String = "Hello!"

// val n: Int = "Hello!" // Error: assigning String value to Int variable


/* 아래와 같이 나눠서 초기화하는 것은 .kt 파일 안에서는 가능하나 스크립트 파일 안에서는 안된다
val text: String

text = "Hello!"
*/

val `fun` = 1
val `name with spaces` = 2

var sum = 1
sum = sum + 2
sum = sum + 3

//var sum = 1
//sum = "Hello" // Error: assigning String value to Int variable

var result = 3
result *= 10 // result = result * 10
result += 6  // result = result + 6

var a = 1
println(a++) // a는 2, 1 출력됨
println(++a) // a는 3, 3 출력됨
println(--a) // a는 2, 2 출력됨
println(a--) // a는 1, 2 출력됨

val nn = 34_721_189

val one: Byte = 1                              // OK
//val tooBigForShort: Short = 100_000            // Error: too big for Short
val million = 1_000_000                        // OK: Int로 타입이 추론됨
//val tooBigForInt: Int = 10_000_000_000         // Error: too big for Int
val tenBillions = 10_000_000_000               // OK: Long으로 타입이 추론됨
//val tooBigForLong = 10_000_000_000_000_000_000 // Error: too big for Long

val hundredLong = 100L     // OK: Long으로 타입이 추론됨
//val hundredInt: Int = 100L // Error: assigning Long to Int

val bin = 0b10101 // 21
val hex = 0xF9    // 249

val zero = 0     // OK
//val zeroOne = 01 // Error

val neg = -10
val negHex = -0xFF

val pi = 3.14
val one2 = 1.0

val quarter = .25 // 0.25
//val one3 = 1.      // Error: exepecting an element
val two = 2       // 오류는 아니지만, 정수 리터럴


val pipi = 0.314E1        // 3.14 = 0.314*10
val pi100 = 0.314E3     // 314.0 = 0.314*1000
val piOver100 = 3.14E-2 // 0.0314 = 3.14/100
val thousand = 1E3      // 1000.0 = 1*1000

val pi2 = 3.14f
val one4 = 1f

//val pi: Double = 3.14f // Error

println(Float.MIN_VALUE)              // 1.4E-45
println(Double.MAX_VALUE)             // 1.7976931348623157E308
println(Double.POSITIVE_INFINITY)     // Infinity
println(1.0/Double.NEGATIVE_INFINITY) // -0.0
println(2 - Double.POSITIVE_INFINITY) // -Infinity
println(3 * Float.NaN) // NaN

println("division and modulo")
println(7/4)           // 1
println(-7/4)          // -1
println(7/(-4))        // -1
println((-7)/(-4))     // 1
println(7%4)           // 3
println(-7%4)          // -3
println(7%(-4))        // 3
println((-7)%(-4))     // -3

println("floor division and modulo")
println(7.floorDiv(4))        // 1
println((-7).floorDiv(4))     // -2
println(7.floorDiv(-4))       // -2
println((-7).floorDiv(-4))    // 1
println(7.mod(4))             // 3
println((-7).mod(4))          // 1
println(7.mod(-4))            // -1
println((-7).mod(-4))         // -3


val byte: Byte = 1
val int = 1
val long = 1L
val float = 1.5f
val double = 1.5
-byte   // -1: Int
-int    // -1: Int
-long   // -1: Long
-float  // -1.5: Float
-double // -1.5: Double


val z = 'z'
val alpha = 'α'

val quote = '\''
val newLine = '\n'

val pi3 = '\u03C0' // π

var a2 = 'a'
var h = 'h'
/* 'a'보다 뒤 5번째 글자  */ println(a2 + 5)   // f
/* 'a'보다 앞 5번째 글자  */ println(a2 - 5)   // \
/* 'a'와 'h' 사이의 거리  */ println(h - a2)  // 7
/* 'h' 바로 앞 글자       */ println(--h)     // g
/* 'a' 바로 뒤 글자       */ println(++a)     // b

val n2 = 100 // Int
//val l: Long = n // Error: can't assign Int to Long

val n3 = 945
println(n3.toByte())  // -79
println(n3.toShort()) // 945
println(n3.toChar())  // α
println(n3.toLong())  // 945

println(2.5.toInt()) // 2
println((-2.5).toInt()) // -2
println(1_000_000_000_000.toFloat().toLong()) // 999999995904

val hasErrors = false;
val testPassed = true;

val x=1
val y=2
println((x == 1) or (y == 1))     // true
println((x == 0) || (y == 0))     // false
println((x == 1) and (y != 1))    // true
println((x == 1) and (y == 1))    // false
println((x == 1) xor (y == 1))    // true
println((x == 1) xor (y != 1))    // false
println(x == 1 || y/(x - 1) != 1) // true
println(x != 1 && y/(x - 1) != 1) // false

val a3 = 1
val b2 = 2
println(a3 == 1 || b2 != 1) // true
println(a3 >= 1 && b2 < 3)  // true
println(a3 < 1 || b2 < 1)   // false
println(a3 > b2) // false

val a4 = 1                // Int
val b3 = 2L               // Long
//println(a4 == b3)          // Error: comparing Int and Long
println(a4.toLong() == b3) // Ok: 두 타입 모두 Long임

val set = sortedSetOf(Double.NaN, Double.NaN,Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,0.0)
println(set) // [-Infinity, 0.0, Infinity, NaN]

val hello = "Hello, world!"

/*
import java.util.Date
fun main() {
  val name = readLine()
  println("Hello, $name!\n Today is ${Date()}")
}
*/

val name = readLine()
val message = """
  Hello, $name!
  Today is ${java.util.Date()}
""".trimIndent()

val message2 = """
This is triple quote:'${"\"\"\""}'
""".trimIndent()

"Hello!".length     // 6
"Hello!".lastIndex  // 5(첫 번째 문자의 인덱스가 0이므로)


val ss = "Hello!"
println(ss[0])  // H
println(ss[1])  // e
println(ss[5])  // !
// println(s2[10]) // 잘못된 인덱스

val sss = "The sum is: " + sum // "The sum is $sum"으로 대신할 수 있음

val s1 = "Hello!"
val s2 = "Hel" + "lo!"
println(s1 == s2) // true

println("abc" < "cba") // true
println("123" > "34")  // false

val aa = emptyArray<String>()      // Array<String> (원소 0개)
val bb = arrayOf("hello", "world") // Array<String> (원소 2개)
val cc = arrayOf(1, 4, 9)          // Array<Int> (원소 3개)

val size = readLine()!!.toInt()
val squares0 = Array(size) { (it + 1)*(it + 1) }

val operations = charArrayOf('+', '-', '*', '/', '%')
val squares = IntArray(10) { (it + 1)*(it + 1) }

val squares2 = arrayOf(1, 4, 9, 16)
squares2.size      // 4
squares2.lastIndex // 3
squares2[3]        // 16
squares2[1]        // 4

squares2[2] = 100 // squares: 1, 4, 100, 16
squares2[3] += 9  // squares: 1, 4, 100, 25
squares2[0]--     // squares: 0, 4, 100, 25

val numbers = squares2
numbers[0] = 1000   // 바뀐 데이터가 squares와 numbers에 공유됨
println(squares2[0]) // prints 1000

val numbers2 = squares2.copyOf()
numbers2[0] = 1000 // squares에는 영향이 없다
squares2.copyOf(2) // 뒤가 잘림: 1, 4
squares2.copyOf(5) // 부족한 부분에 0이 채워짐: 1, 4, 9, 16, 0

var aaaa = arrayOf(1, 4, 9, 16)
//aaaa = arrayOf("one", "two") // Error: can't assign Array<String> to Array<Int>

//Object[] objects = new String[] { "one", "two", "three" };
//objects[0] = new Object(); // ArrayStoreException 예외가 발생함

val strings = arrayOf("one", "two", "three")
//val objects: Array<Any> = strings // 예외

val b = intArrayOf(1, 2, 3) + 4                // 원소를 하나만 추가: 1, 2, 3, 4
val c = intArrayOf(1, 2, 3) + intArrayOf(5, 6) // 다른 배열을 추가: 1, 2, 3, 5, 6

intArrayOf(1, 2, 3) == intArrayOf(1, 2, 3) // false

intArrayOf(1, 2, 3).contentEquals(intArrayOf(1, 2, 3)) // true
