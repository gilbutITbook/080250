import java.util.function.Consumer

val squares = IntArray(5) { n -> n*n } // 0, 1, 4, 9, 16

fun sum1(numbers: IntArray): Int {
  var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")
    
  for (i in 1..numbers.lastIndex) result += numbers[i]
  
  return result
}

fun main1() {
  println(sum1(intArrayOf(1, 2, 3))) // 6
}

main1()

fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
  var result = numbers.firstOrNull()
    ?: throw IllegalArgumentException("Empty array")
    
  for (i in 1..numbers.lastIndex) result = op(result, numbers[i])
  
  return result
}

fun sum(numbers: IntArray) =
  aggregate(numbers, { result, op -> result + op })
  
fun max(numbers: IntArray) =
  aggregate(numbers, { result, op -> if (op > result) op else result })
  
fun main2() {
  println(sum(intArrayOf(1, 2, 3))) // 6
  println(max(intArrayOf(1, 2, 3))) // 3
}

main2()

/*
fun main3() {
  // Error: type mismatch
  val consume: Consumer<String> = { s ->println(s) }
  
  consume.accept("Hello")
}
*/

fun interface StringConsumer {
  fun accept(s:String)
}

fun main4() {
  val consume = StringConsumer { s -> println(s) }
  
  consume.accept("Hello")
}

main4()  // Hello

fun measureTime(action: () -> Unit): Long {
  val start = System.nanoTime()
  
  action()
  
  return System.nanoTime() - start
}

val inc: (Int) -> Int = { n -> n + 1 } // Ok
//val dec: Int -> Int = { n -> n - 1 }   // Error

fun main5() {
  val lessThan: (Int, Int) -> Boolean = { a, b -> a < b }
  println(lessThan(1, 2)) // true
}

main5()

//val lessThan1 = { a, b -> a < b } // error:  cannot infer a type for this parameter. Please specify it explicitly.

val lessThan2 = { a: Int, b: Int -> a < b } // Ok

fun measureTime2(action: (() -> Unit)?): Long {
  val start = System.nanoTime()
  
  action?.invoke()
  
  return System.nanoTime() - start
}

fun main6() {
  println(measureTime2(null))
}

main6()

fun main7() {
  val shifter: (Int) -> (Int) -> Int = { n -> { i ->i + n } }
  
  val inc = shifter(1)
  
  val dec = shifter(-1)
  
  println(inc(10)) // 11
  println(dec(10)) // 9
}

main7()

fun main8() {
  val evalAtZero: ((Int) -> (Int)) -> Int = { f -> f(0) }
  
  println(evalAtZero { n -> n + 1 }) // 1
  println(evalAtZero { n -> n - 1 }) // -1
}

main8()

fun sum2(numbers: IntArray) =
  aggregate(numbers, { result, op -> result + op })
fun max2(numbers: IntArray) =
  aggregate(numbers, { result, op -> if (op > result) op else result })
  
fun main9() {
  println(sum2(intArrayOf(1, 2, 3))) // 6
  println(max2(intArrayOf(1, 2, 3))) // 3
}

main9()

fun sum3(numbers: IntArray) =
  aggregate(numbers) { result, op -> result + op }

fun max3(numbers: IntArray) =
  aggregate(numbers) { result, op -> if (op > result) op else result }

fun measureTime3(action: () -> Unit): Long {
  val start = System.nanoTime()
  action()
  return System.nanoTime() - start
}

val time = measureTime3{ 1 + 2 }

fun check(s: String, condition: (Char) -> Boolean): Boolean {
  for (c in s) {
    if (!condition(c)) return false
  }
  return true
}

fun main10() {
  println(check("Hello") { c ->c.isLetter() }) // true
  println(check("Hello") { it.isLowerCase() }) // false
}

main10()

fun check2(s: String, condition: (Int, Char) -> Boolean): Boolean {
  for (i in s.indices) {
    if (!condition(i, s[i])) return false
  }
  return true
}

fun main11() {
  println(check2("Hello") { _, c ->c.isLetter() })              // true
  println(check2("Hello") { i, c ->i == 0 || c.isLowerCase() }) // true
}

main11()

fun sum4(numbers: IntArray) =
  aggregate(numbers, fun(result, op) = result + op)

fun sum5(numbers: IntArray) =
  aggregate(numbers, fun(result, op): Int { return result + op })

fun forEach(a: IntArray, action: (Int) -> Unit) {
  for (n in a) {
    action(n)
  }
}

fun main12() {
  var sum = 0
  forEach(intArrayOf(1, 2, 3, 4)) {
    sum += it
  }
  println(sum) // 10
}

main12()

fun check3(s: String, condition: (Char) -> Boolean): Boolean {
  for (c in s) {
    if (!condition(c)) return false
  }
  
  return true
}

fun isCapitalLetter(c: Char) = c.isUpperCase() && c.isLetter()

fun main13() {
  println(check3("Hello") { c -> isCapitalLetter(c) }) // false
  // 또는 
  println(check3("Hello") { isCapitalLetter(it) }) // false
}

main13()

fun main14() {
  println(check("Hello", ::isCapitalLetter)) // false
}

main14()

fun evalAtZero(f: (Int) -> Int) = f(0)

fun inc(n: Int) = n + 1
fun dec(n: Int) = n - 1

fun main15() {
  fun dec(n: Int) = n - 1
  println(evalAtZero(::inc)) // 1
  println(evalAtZero(::dec)) // -1
}

main15()

class Person(val firstName: String, val familyName: String)

fun main16() {
  val createPerson= ::Person
  createPerson("John", "Doe")
}

main16()

class Person2(val firstName: String, val familyName: String) {
  fun hasNameOf(name: String) = name.equals(firstName, 
                                            ignoreCase = true)
}

fun main17() {
  val isJohn = Person2("John", "Doe")::hasNameOf
  
  println(isJohn("JOHN")) // true
  println(isJohn("Jake")) // false
}

main17()

fun max4(a: Int, b: Int) = if (a > b) a else b
fun max4(a: Double, b: Double) = if (a > b) a else b

val f: (Int, Int) -> Int = ::max4 // Ok
//val g = ::max4                    // error: overload resolution ambiguity                  

fun max5(a: Int, b: Int) = if (a > b) a else b

fun main18() {
  println((::max5)(1, 2)) // 2
  //println(::max5(1, 2))   // Error: this syntax is reserved for future use
}

main18()

class Person3(var firstName: String, var familyName: String)

fun main19() {
  val person = Person3("John", "Doe")
  val readName = person::firstName.getter     // 게터 참조
  val writeFamily = person::familyName.setter // 세터 참조
  
  println(readName())        // John
  writeFamily("Smith")
  println(person.familyName) // Smith
}

main19()

inline fun indexOf(numbers: IntArray, condition: (Int) -> Boolean): Int {
  for (i in numbers.indices) {
    if (condition(numbers[i])) return i
  }
  
  return -1
}

fun main20() {
  println(indexOf(intArrayOf(4, 3, 2, 1)) { it < 3 }) // 2
}

main20()

fun main21() {
  val numbers = intArrayOf(4, 3, 2, 1)
  var index = -1

  for (i in numbers.indices) {
    if (numbers[i] < 3) {
      index = i
      break
    }
  }
  
  println(index)
}

main21()

var lastAction: () -> Unit = {}

inline fun runAndMemorize(action: () -> Unit) {
  action()
  //lastAction = action // error: illegal usage of inline-parameter ...
}

/*
inline fun forEach2(a: IntArray, action: ((Int) -> Unit)?) { // error: inline-parameter 'action'...
  if (action == null) return
  for (n in a) action(n)
}
*/

//warning: expected performance impact from inlining is insignificant.
inline fun forEach3(a: IntArray, noinline action: ((Int) -> Unit)?) {
  if (action == null) return
  for (n in a) action(n)
}

/*
class Person4(private val firstName: String, 
              private val familyName: String) {
  inline fun sendMessage(message: () -> String) {
    println("$firstName $familyName: ${message()}") // error: public-API inline function cannot access non-public-API
  }
}
*/

/*
class Person5(private val firstName: String,
              private val familyName: String) {

  inline var age = 0 // error: inline property cannot have backing field
  // error: public-API inline function cannot access non-public-API '...'
  inline val fullName get() = "$firstName $familyName"
}
*/

fun forEach4(a: IntArray, action: (Int) -> Unit) {
  for (n in a) action(n)
}

/*
fun main22() {
  forEach4(intArrayOf(1, 2, 3, 4)) {
    if (it < 2 || it > 3) return
      println(it) // error: 'return' is not allowed here
  }
}

main22()
*/

fun main23() {
  forEach3(intArrayOf(1, 2, 3, 4), fun(it: Int) {
    if (it < 2 || it > 3) return
    println(it)
  })
}

main23()

val action: (Int) -> Unit = myFun@ {
  if (it < 2 || it > 3) return@myFun
  println(it)
}

forEach3(intArrayOf(1, 2, 3, 4)) {
  if (it < 2 || it > 3) return@forEach3
  println(it)
}

fun main24(args: Array<String>) {
  if (args.isEmpty()) return@main24
  println(args[0])
}

main24(arrayOf("1","2","3")) // 1
main24(arrayOf())

inline fun forEach5(a: IntArray, action: (Int) -> Unit) {  for (n in a) action(n) }

fun main25() {
  forEach5(intArrayOf(1, 2, 3, 4)) {
    if (it < 2 || it > 3) return // main에서 반환됨
    println(it)
  }
}

main25()

/*
inline fun forEach6(a: IntArray, action: (Int) -> Unit) = object {
  fun run() {
    for (n in a) {
      action(n) // error: can't inline 'action' here: it may contain non-local returns.
    }
  }
}
*/

inline fun forEach7(
  a: IntArray, crossinline action: (Int) -> Unit
) = object {
  fun run() {
    for (n in a) {
      action(n) // Ok
    }
  }
}

/*
fun main26() {
  forEach7(intArrayOf(1, 2, 3, 4)){
    if (it < 2 || it > 3) return //  error: 'return' is not allowed here
    println(it)
  }
}

main26()
*/

/*
while (true) {
  forEach7(intArrayOf(1, 2, 3, 4)) {
    // error: 'break' or 'continue' jumps across a function or a class boundary
    if (it < 2 || it > 3) break 
    println(it)
  }
}
*/

fun String.truncate(maxLength: Int): String {
  return if (length <= maxLength) this else substring(0, maxLength)
}

fun main27() {
  println("Hello".truncate(10)) // Hello
  println("Hello".truncate(3))  // Hel
}

main27()

class Person6(val name: String, private val age: Int)

//fun Person6.showInfo() = println("$name, $age") // error: cannot access 'age'

class Person7(val name: String, private val age: Int) {
  // Ok: age에 접근할 수 있음
  fun Person7.showInfo() = println("$name, $age")
}

// $ kotlinc HasName.kt -include-runtime -d HasName.jar
// $ java -jar HasName.jar
// true
// false

// $ kotlinc FullName.kt -include-runtime -d FullName.jar
// FullName.kt:5:12: warning: extension is shadowed by a member: public final fun fullName(): String
// fun Person.fullName() = "$familyName $firstName"
//            ^
// $ java -jar FullName.jar
// John Doe

// $ kotlinc Truncator.kt -include-runtime -d Truncator.jar
// $ java -jar Truncator.jar


// $ kotlinc UtilMain.kt Util.kt -include-runtime -d UtilMain.jar
// $ java -jar UtilMain.jar
// Hel

// 널이 될 수 있는 수신 객체 타입
fun String?.truncate2(maxLength: Int): String? {
  if (this == null) return null
  return if (length <= maxLength) this else substring(0, maxLength)
}

fun main28() {
  println("input string:")
  val s = readLine()     // 널이 될 수 있는 문자열
  println(s.truncate2(3)) // 여기서 `?.`를 쓰지 않아도 된다.
}

main28()

val IntRange.leftHalf: IntRange
  get() = start..(start + endInclusive)/2

fun main29() {
  println((1..3).leftHalf) // 1..2
  println((3..6).leftHalf) // 3..4
}

main29()

val IntArray.midIndex
  get() = lastIndex/2
  
var IntArray.midValue
  get() = this[midIndex]
  set(value) {
    this[midIndex] = value
  }

fun main30() {
  val numbers = IntArray(6) { it*it } // 0, 1, 4, 9, 16, 25
  
  println(numbers.midValue) // 4
  numbers.midValue *= 10
  println(numbers.midValue) // 40
}

main30()

val String.message by lazy { "Hello" }

fun main31() {
  println("Hello".message) // Hello
  println("Bye".message)   // Hello
}

main31()

object Messages

val Messages.HELLO by lazy { "Hello" }

fun main32() {
  println(Messages.HELLO)
}

main32()

fun IntRange.Companion.singletonRange(n: Int) = n..n

fun main33() {
  println(IntRange.singletonRange(5))           // 5..5
  println(IntRange.Companion.singletonRange(3)) // 3..3
}

main33()

val String.Companion.HELLO
  get() = "Hello"

fun main34() {
  println(String.HELLO)
  println(String.Companion.HELLO)
}

main34()

class Person8(val firstName: String, val familyName: String) {
  companion object
}

val Person8.Companion.UNKNOWN by lazy { Person8("John", "Doe") }

// error: unresolved reference: Companion
//fun Any.Companion.sayHello() = println("Hello")

fun aggregate2(numbers: IntArray, op: Int.(Int) -> Int): Int {
  var result = numbers.firstOrNull()
      ?: throw IllegalArgumentException("Empty array")
  
  for (i in 1..numbers.lastIndex) result = result.op(numbers[i])
  
  return result
}

fun sum6(numbers: IntArray) = aggregate2(numbers) { op -> this + op }

fun sum7(numbers: IntArray) = aggregate2(numbers, fun Int.(op: Int) = this + op)

fun aggregate3(numbers: IntArray, op: Int.(Int) -> Int): Int {
  var result = numbers.firstOrNull()
      ?: throw IllegalArgumentException("Empty array")
  
  for (i in 1..numbers.lastIndex) {
    result = op(result, numbers[i]) // 비 확장 함수 호출
  }
  
  return result
}

val min1: Int.(Int) -> Int = { if (this < it) this else it }
val min2: (Int, Int) -> Int = min1  // 수신 객체가 첫번째 파라미터인 일반 함수 타입
val min3: Int.(Int) -> Int = min2   // 수신 객체가 있는 함수 타입

fun main35() {
  val min1: Int.(Int) -> Int = { if (this < it) this else it }
  val min2: (Int, Int) -> Int = min1
  println(3.min1(2))  // Ok: min1을 확장 함수로 호출함
  println(min1(1, 2)) // Ok: min1을 비확장 함수로 호출함
  //println(3.min2(2))  // Error: Can’t call min2 as extension
  println(min2(1, 2)) // Ok: min2를 비확장 함수로 호출함
}

main35()

// $ kotlinc IntMax.kt -include-runtime -d IntMax.jar
// $ java -jar IntMax.jar

fun aggregate4(numbers: IntArray, op: Int.(Int) -> Int): Int {
  var result = numbers.firstOrNull()
      ?: throw IllegalArgumentException("Empty array")
      
  for (i in 1..numbers.lastIndex) result = result.op(numbers[i])
  
  return result
}

fun max(a: Int, b: Int) = if (a > b) a else b

fun main36() {
  println(aggregate4(intArrayOf(1, 2, 3, 4), ::max))
}

main36()

// $ kotlinc IntMax2.kt -include-runtime -d IntMax2.jar
// $ java -jar IntMax2.jar

class Address {
  var zipCode: Int = 0
  var city: String = ""
  var street: String = ""
  var house: String = ""
  
  fun post(message: String): Boolean {
    println("Message for {$zipCode, $city, $street, $house}: $message\nInput OK or other string")
    return readLine() == "OK"
  }
}

fun main37() {
  val isReceived = Address().run {
    // Address 인스턴스를 this로 사용할 수 있다
    zipCode = 123456
    city = "London"
    street = "Baker Street"
    house = "221b"
    post("Hello!") // 반환값
  }
  
  if (!isReceived) {
    println("Message is not delivered")
  }
}

main37()

fun Address.showCityAddress() = println("$street, $house")

fun main38() {
  Address().run {
    zipCode = 123456
    city = "London"
    street = "Baker Street"
    house = "221b"
    showCityAddress()
  }
}

main38()

class Address2(val city: String, val street: String, val house: String) {
  fun asText() = "$city, $street, $house"
}

fun main39() {
  val addr = Address2("London", "Baker Street", "221b")
  val message = "Address: ${addr.city}, ${addr.street}, ${addr.house}"
  println(message)
}

main39()

fun main40() {
  val address = Address2("London", "Baker Street", "221b")
  println(address.asText())
}

main40()

fun main41() {
  pritln("Input city:")
  val city = readLine() ?: return
  pritln("Input street:")
  val street = readLine() ?: return
  pritln("Input house:")
  val house = readLine() ?: return
  val address = Address2(city, street, house)
  println(address.asText())
}

main41()

fun main42() {
  println("Input city, street, house(press 'enter' after input each):")
  val address = Address2(readLine() ?: return,
                        readLine() ?: return,
                        readLine() ?: return)
  println(address.asText())
}

main42()

fun main43() {
  val address = run {
    println("Input city, street, house(press 'enter' after input each):")
    val city = readLine() ?: return
    val street = readLine() ?: return
    val house = readLine() ?: return
    Address2(city, street, house)
  }
   
  println(address.asText())
}

main43()

/*
fun main44() {
  val address = {
    println("Input city, street, house(press 'enter' after input each):")
    val city = readLine() ?: return // Error: return is not allowed
    val street = readLine() ?: return // Error: return is not allowed
    val house = readLine() ?: return // Error: return is not allowed
    Address2(city, street, house)
  }
  
  println(address.asText()) // Error: no asText() method
}

main44()
*/

class Address3(val city: String, val street: String, val house: String) {
  fun post(message: String) {}
}

fun main45() {
  Address3("London", "Baker Street", "221b").let {
    // 이 안에서는 it 파라미터를 통해 Address 인스턴스에 접근할 수 있음
    println("To city: ${it.city}")
    it.post("Hello")
  }
}

main45()

fun main46() {
  Address3("London", "Baker Street", "221b").let { addr ->
    // 이 안에서는 addr 파라미터를 통해 Address 인스턴스에 접근할 수 있음
    println("To city: ${addr.city}")
    addr.post("Hello")
  }
}

main46()

fun readInt() = try {
  println("Input int:")
  readLine()?.toInt()
} catch (e: NumberFormatException) {
  null
}

fun main47(args: Array<String>) {
  println("Input int:")
  val index = readInt()
  val arg = if (index != null) args.getOrNull(index) else null
  if (arg != null) {
    println(arg)
  }
}

main47(arrayOf("1","2","3"))

class Address4 {
  var city: String = ""
  var street: String = ""
  var house: String = ""
  
  fun post(message: String) { }
}

fun main48() {
  println("Input string:")
  val message = readLine() ?: return
  
  Address4().apply {
    city = "London"
    street = "Baker Street"
    house = "221b"
  }.post(message)
}

main48()

fun main49() {
  println("Input string:")
  val message = readLine() ?: return
  
  Address4().also {
    it.city = "London"
    it.street = "Baker Street"
    it.house = "221b"
  }.post(message)
}

class Address5(val city: String, val street: String, val house: String)

class Person9(val firstName: String, val familyName: String) {
  fun Address5.post(message: String) {
    // 암시적 this: 확장 수신 객체(Address)
    val city = city
    // 한정시키지 않은 this: 확장 수신 객체(Address)
    val street = this.city
    // 한정시킨 this: 확장 수신 객체(Address)
    val house = this@post.house
    // 암시적 this: 디스패치 수신 객체(Person)
    val firstName = firstName
    // 한정시킨 this: 디스패치 수신 객체(Person)
    val familyName = this@Person9.familyName
    
    println("From $firstName, $familyName at $city, $street, $house:")
    println(message)
  }
  
  fun test(address: Address) {
    // 디스패치 수신 객체: 암시적
    // 확장 수신 객체: 명시적
    address.post("Hello")
  }
}

class Address6(val city: String, val street: String, val house: String)

class Person10(val firstName: String, val familyName: String) {
  fun Address6.post(message: String) { }
  inner class Mailbox {
    fun Person10.testExt(address: Address6) {
      address.post("Hello")
    }
  }
}

fun Person10.testExt(address: Address6) {
  address.post("Hello")
}

// $ kotlinc AddressPost.kt -include-runtime -d AddressPost.jar

// $ kotlinc AddressPost2.kt -include-runtime -d AddressPost2.jar

// $ kotlinc AddressPost3.kt -include-runtime -d AddressPost3.jar
// $ java -jar AddressPost3.jar

// $ kotlinc AddressPost4.kt -include-runtime -d AddressPost4.jar
// $ java -jar AddressPost4.jar

class Address7(val city: String, val street: String, val house: String) {
  fun Address7.post(message: String) { }
}

// $ kotlinc PersonCompanion.kt -include-runtime -d PersonCompanion.jar
// $ java -jar PersonCompanion.jar
