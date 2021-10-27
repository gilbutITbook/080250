import kotlin.math.PI
import java.lang.NumberFormatException


// CircleArea.kts

// ReadLine.kts

/*
fun increment(n: Int): Int {
  return n++ // Error: can’t change immutable variable
}
*/

// Increment.kts

fun prompt(name: String) {
  println("***** Hello, $name! *****")
}

fun prompt2(name: String): Unit {
  println("***** Hello, $name! *****")
}


fun circleArea(radius: Double): Double = PI*radius*radius

fun circleArea2(radius: Double) = PI*radius*radius // 반환값이 Double로 추론됨

fun circleArea3(radius: Double) = { PI*radius*radius }

/*
fun circleArea4(radius: Double) = {
    return PI*radius*radius    // error: 'return' is not allowed here
                               // error: type mismatch: inferred type is () -> [ERROR : Return not allowed] but Double was expected
}
*/

// RectangleArea.kts

// Swap.kts

fun readInt() = readLine()!!.toInt()
fun readInt(radix: Int) = readLine()!!.toInt(radix)

object O {
  fun plus(a: String, b: String) = a + b
  //fun plus(a: String, b: String) = a.toInt() + b.toInt()  // error: conflicting overloads: public final fun plus(a: String, b: String): String defined...
}

fun mul(a: Int, b: Int) = a*b            // 1
fun mul(a: Int, b: Int, c: Int) = a*b*c  // 2
fun mul(s: String, n: Int) = s.repeat(n) // 3
fun mul(o: Any, n: Int) = Array(n) { o } // 4

mul(1, 2)   // Int가 Any의 하위타입이므로 1과 4중에 1을 선택
// mul(1, 2L) // error: none of the following functions can be called with the arguments supplied
            // 오류: (Int, Long) 타입을 받을 수 있는 함수가 없음
mul(1L, 2)  // Long, Int 타입을 받을 수 있는 함수는 4번뿐이므로 4를 선택
mul("0", 3) // String이 Any의 하위타입이기 때문에 3과 4중에 3을 선택

mul("0" as Any, 3) // (Any, Int)를 받을 수 있는 함수는 4뿐이므로 4를 선택

fun readInt2() = readInt(10)

fun readInt3(radix: Int = 10) = readLine()!!.toInt(radix)

val decimalInt = readInt3()
val decimalInt2 = readInt3(10)
val hexInt = readInt3(16)

// RestrictToRange.kts

fun mul2(a: Int, b: Int = 1) = a*b           // 1
fun mul2(a: Int, b: Long = 1L) = a*b         // 2
fun mul2(a: Int, b: Int, c: Int = 1) = a*b*c // 3

//mul2(10)         // error: overload resolution ambiguity
                 // 오류: 1과 2 사이에 어느 쪽을 호출할지 결정할 수 없음
mul2(10, 20)     // 인자가 더 적기 때문에 1과 3 중에 1을 선택
mul2(10, 20, 30) // 적용 가능한 함수가 3번뿐이어서 3을 선택

// PrintSorted.kts

// Change.kts

fun printSorted(vararg items: Int) {
  items.sort()
  println(items.contentToString())
}

printSorted(6, 1, *intArrayOf(3, 8), 2) // 6,1,3,8,2 순서로 원소가 들어있는 배열이 전달되고, [1, 2, 3, 6, 8]이 반환됨

printSorted(items = intArrayOf(1, 2, 3)) // vararg의 이름을 지정할 때는 배열을 그냥 넘기면 됨
//printSorted(items = 1, 2, 3) // error: assigning single elements to varargs in named form is forbidden 
//                             // error: the integer literal does not conform to the expected type IntArray

// PrintSorted2.kts

// PrintSorted3.kts

// PrintSorted4.kts

// ReadIntPair.kts

// Swap2.kts

// ReadInt.kts

// Import.kts

// Import2.kts

// If.kts

// Max.kts

/*
val a = 10
val b = 20
val max = if (a > b) a // error: 'if' must have both main and 'else' branches if used as an expression
*/

// RenamePackage.kts

val chars = 'a'..'h'    // 'a'부터 'h'까지의 모든 문자
val twoDigits = 10..99   // 10부터 99까지의 모든 수
val zero2One = 0.0..1.0 // 0부터 1까지의 모든 부동소수점 수

val num = readLine()!!.toInt()
println(num in 10..99) // num >= 10 && num <= 99

println(num !in 10..99) // !(num in 10.99)

println("def" in "abc".."xyz") // true
println("zzz" in "abc".."xyz") // false

val twoDigits2 = 10 until 100 // 10..99와 같음. 100은 포함되지 않음

println(5 in 5..5)      // true
println(5 in 5 until 5) // false
println(5 in 10..1)     // false

println(5 in 10 downTo 1) // true
println(5 in 1 downTo 10) // false: 빈 진행임

println((1..10 step 3).joinToString())       // 1, 4, 7, 10
println((15 downTo 9 step 2).joinToString()) // 15, 13, 11, 9

println((1..12 step 3).joinToString())       // 1, 4, 7, 10: 1..10 step 3과 같음
println((15 downTo 8 step 2).joinToString()) // 15, 13, 11, 9: 15 downTo 9 step 2와 같음

println("Hello, World".substring(1..4))               // ello
println("Hello, World".substring(1 until 4))         // ell
println("Hello, World".substring(1, 4))               // ell: substring(1 until 4)와 같음
println(IntArray(10) { it*it }.sliceArray(2..5).joinToString())      // 4, 9, 16, 25
println(IntArray(10) { it*it }.sliceArray(2 until 5).joinToString()) // 4, 9, 16

// Range.kt

val numbers = intArrayOf(3, 7, 2, 1)
val text = "Hello!"
println(2 in numbers)  // true
println(9 !in numbers) // true
println(4 in numbers)  // false
println('a' in text)   // false
println('H' in text)   // true
println('h' !in text)  // true

fun hexDigit(n: Int): Char {
  if (n in 0..9) return '0' + n
  else if (n in 10..15) return 'A' + n - 10
  else return '?'
}

fun hexDigit2(n: Int): Char {
  when {
    n in 0..9 -> return '0' + n
    n in 10..15 -> return 'A' + n - 10
    else -> return '?'
  }
}

fun hexDigit3(n: Int) = when {
  n in 0..9 -> '0' + n
  n in 10..15 -> 'A' + n - 10
  else -> '?'
}

fun numberDescription(n: Int): String = when {
  n == 0 -> "Zero"
  n == 1 || n == 2 || n == 3 -> "Small"
  n in 4..9 -> "Medium"
  n in 10..100 -> "Large"
  n !in Int.MIN_VALUE until 0 -> "Negative"
  else -> "Huge"
}

fun numberDescription2(n: Int, max: Int = 100): String = when (n) {
  0 -> "Zero"
  1, 2, 3 -> "Small"
  in 4..9 -> "Medium"
  in 10..max -> "Large"
  !in Int.MIN_VALUE until 0 -> "Negative"
  else -> "Huge"
}

fun readHexDigit() = when(val n = readLine()!!.toInt()) { // n을 정의
  in 0..9 -> '0' + n
  in 10..15 -> 'A' + n - 10
  else -> '?'
}

// While.kts

// While2.kts

// For.kts

fun parseIntNumber(s: String, fallback: Int = -1): Int {
  var num = 0
  
  if (s.length !in 1..31) return fallback
  
  for (c in s) {
    if (c !in '0'..'1') return fallback
    num = num*2 + (c - '0')
  }
  return num
}

println(parseIntNumber("1"))          // 1
println(parseIntNumber("1123123123")) // -1
println(parseIntNumber("11111111"))   // 255


val a = IntArray(10) { it*it } // 0, 1, 4, 9, 16, ...

println(a.joinToString())

for (i in 0..a.lastIndex) {    // 0, 1, 2, 3, ...
  if (i % 2 == 0) {            // 0, 2, 4, 6, ...
    a[i] *= 2
  }
}

println(a.joinToString())      // 0, 1, 8, 9, 32 ...

for (i in 0..a.lastIndex step 2) { // 0, 2, 4, 6, ...
  a[i] *= 2        
}

println(a.joinToString())      // 0, 1, 8, 9, 32 ...

val a2 = IntArray(10) { it*it } // 0, 1, 4, 9, 16, ...
for (i in a.indices step 2) {  // 0, 2, 4, 6, ...
  a2[i] *= 2
}

println(a2.joinToString())


// Break.kts

// Break2.kts

fun indexOf(subarray: IntArray, array: IntArray): Int {
  outerLoop@ for (i in array.indices) {
    for (j in subarray.indices) {
      if (subarray[j] != array[i + j]) continue@outerLoop
    }
    return i
  }
  
  return -1
}

println(indexOf(intArrayOf(2,3), intArrayOf(1,2,3,4,5)))

tailrec fun binIndexOf(
  x: Int,
  array: IntArray,
  from: Int = 0,
  to: Int = array.size
): Int {
  if (from == to) return -1
  val midIndex = (from + to - 1) / 2
  val mid = array[midIndex]
  return when {
    mid < x ->binIndexOf(x, array, midIndex + 1, to)
    mid > x ->binIndexOf(x, array, from, midIndex)
    else ->midIndex
  }
}

fun binIndexOf2(
  x: Int,
  array: IntArray,
  from: Int = 0,
  to: Int = array.size
): Int {
  var fromIndex = from
  var toIndex = to
  
  while (true) {
    if (fromIndex == toIndex) return -1
    val midIndex = (fromIndex + toIndex - 1) / 2
    val mid = array[midIndex]
    
    when {
      mid < x ->fromIndex = midIndex + 1
      mid > x ->toIndex = midIndex
      else -> return midIndex
    }
  }
}

tailrec fun sum(array: IntArray, from: Int = 0, to: Int = array.size): Int {
  // Warning: not a tail-recursive call
  return if (from < to) return array[from] + sum(array, from + 1, to) else 0
}

tailrec fun sum2(a: Int, b: Int): Int {
  return a + b // Warning: no tail-recursive calls
}

fun parseIntNumber2(s: String): Int {
  var num = 0
  
  if (s.length !in 1..31) throw NumberFormatException("Not a number: $s")
  
  for (c in s) {
    if (c !in '0'..'1') throw NumberFormatException("Not a number: $s")
    num = num*2 + (c - '0')
  }
  
  return num
}

fun sayHello(name: String) {
  val message =
    if (name.isNotEmpty()) "Hello, $name"
    else throw IllegalArgumentException("Empty name")
    
  println(message)
}

//import java.lang.NumberFormatException
fun readInt4(default: Int): Int {
  try {
    return readLine()!!.toInt()
  } catch (e: NumberFormatException) {
    return default
  }
}

//import java.lang.NumberFormatException
fun readInt5(default: Int): Int {
  try {
    return readLine()!!.toInt()
  } catch (e: Exception) {
    return 0
  } catch (e: NumberFormatException) {
    return default // 죽은 코드
  }
}

//import java.lang.NumberFormatException
fun readInt6(default: Int) = try {
  readLine()!!.toInt()
} catch (e: NumberFormatException) {
  default
}

//import java.lang.NumberFormatException
fun readInt7(default: Int) = try {
  readLine()!!.toInt()
} finally {
  println("Error")
}

