import java.io.File
import java.util.Date

class Person {
  var firstName: String = ""
  var familyName: String = ""
  var age: Int = 0
  
  fun fullName() = "$firstName $familyName"

  fun showMe() {
    println("${fullName()}: $age")
  }
}

class Person2 {
  var firstName: String = ""
  var familyName: String = ""
  var age: Int = 0
  
  fun fullName() = "${this.firstName} ${this.familyName}"
  fun showMe() {
    println("${this.fullName()}: ${this.age}")
  }
}

fun main1() {
  val person = Person() //  Person 인스턴스 생성
  
  person.firstName = "John"
  person.familyName = "Doe"
  person.age = 25
  
  person.showMe() // John Doe: 25
}

main1()

class Person3 {
  // 생성자로 초기화할 방법이 없으면 모든 클래스가 
  // firstName에 대해 같은 값을 사용하게 됨
  val firstName = "John"
}

class Person4(firstName: String, familyName: String) {
  val fullName = "$firstName $familyName"
}

fun main2() {
  val person = Person4("John", "Doe") // 새 Person 인스턴스 생성
  println(person.fullName)           // John Doe
}

main2()

class Person5(firstName: String, familyName: String) {
  val fullName = "$firstName $familyName"
  
  init {
    println("Created new Person instance: $fullName")
  }
}

/*
class Person6(firstName: String, familyName: String) {
  val fullName = "$firstName $familyName"
  
  init {
    if (firstName.isEmpty() && familyName.isEmpty()) return // error: 'return' is not allowed here
    println("Created new Person instance: $fullName")
  }
}
*/

class Person7(fullName: String) {
  val firstName: String
  val familyName: String
  init {
    val names = fullName.split(" ")
    if (names.size != 2) {
      throw IllegalArgumentException("Invalid name: $fullName")
    }
    firstName = names[0]
    familyName = names[1]
  }
}

fun main3() {
  val person = Person7("John Doe")
  println(person.firstName) // John
}

main3()

/*
class Person8(fullName: String) {
  // error: property must be initialized or be abstract
  val firstName: String
  // error: property must be initialized or be abstract
  val familyName: String
  init {
    val names = fullName.split(" ")
    if (names.size == 2) {
      firstName = names[0]
      familyName = names[1]
    }
  }
}
*/

/*
class Person9(firstName: String, familyName: String) {
  val fullName = "$firstName $familyName"
  fun printFirstName() {
    println(firstName) // error: first name is not available here
  }
}
*/

class Person10(firstName: String, familyName: String) {
  val firstName = firstName // firstName은 생성자 파라미터를 가리킴
  val fullName = "$firstName $familyName"
  fun printFirstName() {
    println(firstName) // Ok: 여기서 firstName은 멤버 프로퍼티를 가리킴
  }
}

class Person11(val firstName: String, familyName: String) {
  // firstName은 생성자 파라미터를 가리킴
  val fullName = "$firstName $familyName"
  
  fun printFirstName() {
    println(firstName) // firstName은 멤버 프로퍼티를 가리킴
  }
}

fun main4() {
  val person = Person11("John", "Doe")
  println(person.firstName) // firstName은 프로퍼티를 가리킴
}

main4()

class Person12(val firstName: String, val familyName: String = "") {
}

class Person13(val firstName: String, val familyName: String = "")

class Person14(val firstName: String, val familyName: String = "") {
  fun fullName() = "$firstName $familyName"
}

class Room(vararg val persons: Person14) {
  fun showNames() {
    for (person in persons) println(person.fullName())
  }
}

fun main5() {
  val room = Room(Person14("John"), Person14("Jane", "Smith"))
  room.showNames()
}

main5()

class Person15 {
  val firstName: String
  val familyName: String
  
  constructor(firstName: String, familyName: String) {
    this.firstName = firstName
    this.familyName = familyName
  }
  
  constructor(fullName: String) {
    val names = fullName.split(" ")
    if (names.size != 2) {
      throw IllegalArgumentException("Invalid name: $fullName")
    }
    firstName = names[0]
    familyName = names[1]
  }
}

class Person16 {
  val fullName: String
  constructor(firstName: String, familyName: String):
    this("$firstName $familyName")
  constructor(fullName: String) {
    this.fullName = fullName
  }
}

class Person17(val fullName: String) {
  constructor(firstName: String, familyName: String):
    this("$firstName $familyName")
}

/*
class Person18 {
  constructor(val fullName: String) // error: 'val' on secondary constructor parameter is not allowed
}
*/

class Person19(private val firstName: String,
             private val familyName: String) {
  fun fullName() = "$firstName $familyName"
}

/*
fun main6() {
  val person = Person19("John", "Doe")
  println(person.firstName)  // error: cannot access 'firstName': it is private in 'Person'
  println(person.fullName()) // Ok
}

main6()
*/

class Empty private constructor() {
  fun showMe() = println("Empty")
}

/*
fun main7() {
  Empty().showMe() // error: cannot access '<init>': it is private in 'Empty'
}
*/

class Person20 (val id: Id, val age: Int) {
  class Id(val firstName: String, val familyName: String)
  fun showMe() = println("${id.firstName} ${id.familyName}, $age")
}

fun main8() {
  val id = Person20.Id("John", "Doe")
  val person = Person20(id, 25)
  person.showMe()
}

main8()

/*
class Person21 (private val id: Id, private val age: Int) {
  class Id(private val firstName: String,
           private val familyName: String) {
    fun nameSake(person: Person) = person.id.firstName == firstName
  }

  //  error: cannot access 'firstName': it is private in 'Id'
  fun showMe() = println("${id.firstName} ${id.familyName}, $age")  
}
*/

class Person22(val firstName: String, val familyName: String) {
  inner class Possession(val description: String) {
    fun showOwner() = println(fullName())
  }
  private fun fullName() = "$firstName $familyName"
}

fun main9() {
  val person = Person22("John", "Doe")
  // Possession 생성자 호출
  val wallet = person.Possession("Wallet")
  wallet.showOwner() // John Doe
}

main9()

class Person23(val firstName: String, val familyName: String) {
  inner class Possession(val description: String) {
    fun showOwner() = println(fullName())
  }

  // this.Possession("Wallet")와 같음
  val myWallet = Possession("Wallet")
  
  fun fullName() = "$firstName $familyName"
}

class Person24(val firstName: String, val familyName: String) {
  inner class Possession(val description: String) {
    fun getOwner() = this@Person24
  }
}

/*
fun main10() {
  class Point(val x: Int, val y: Int) {
    fun shift(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
    override fun toString() = "($x, $y)"
  }
  val p = Point(10, 10)
  println(p.shift(-1, 3)) // (9, 13)
}

fun foo() {
  println(Point(0, 0)) // Error: can’t resolve Point
}

main10()
*/

fun main11() {
  var x = 1
  
  class Counter {
    fun increment() {
      x++
    }
  }
  
  Counter().increment()
  
  println(x) // 2
}

main11()

fun main12(args: Array<String>) {
  class Foo {
    val length = args.size
    inner class Bar {
      val firstArg = args.firstOrNull()
    }
  }
}

main12(arrayOf("a","b"))


fun isLetterString(s: String): Boolean {
  if (s.isEmpty()) return false
  for (ch in s) {
    if (!ch.isLetter()) return false
  }
  return true
}
/*
fun main13() {
  println(isLetterString("abc")) // Ok
  println(isLetterString(null))  // error: null can not be a value of a non-null type String
}

main13()
*/
fun isBooleanString(s: String?) = s == "false" || s == "true"

/*
fun main14() {
  println(isBooleanString(null)) // Ok
  val s: String? = "abc"         // Ok
  val ss: String = s             // error: type mismatch: inferred type is String? but String was expected 
}

main14()
*/

/*
fun isLetterString2(s: String?): Boolean {
  //  error: only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
  if (s.isEmpty()) return false
  
  //  error: not nullable value required to call an 'iterator()' method on for-loop range
  for (ch in s) {
    if (!ch.isLetter()) return false
  }
  return true
}
*/

// 역자 각주 참조
println(null+null)  // nullnull (문자열)

println(null.toString())  // null (문자열)

fun isLetterString3(s: String?): Boolean {
  if (s == null) return false
  
  // s 는 여기서 널이 될 수 없다
  if (s.isEmpty()) return false
  
  for (ch in s) {
    if (!ch.isLetter()) return false
  }
  
  return true
}

fun describeNumber(n: Int?) = when (n) {
  null -> "null"
  // 이하에 있는 가지에서 n은 널이 될 수 없다
  in 0..10 -> "small"
  in 11..100 -> "large"
  else -> "out of range"
}

fun isSingleChar(s: String?) = s != null && s.length == 1

/*
var s = readLine() // String?
if (s != null) {
  s = readLine()
  // 변수 값이 바뀌므로 스마트캐스트를 쓸 수 없음
  // error: only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
  println(s.length)
}
*/

println("Input Integer:")
val n = readLine()!!.toInt()

fun main15() {
  var name: String? = null
  
  fun initialize() {
    name = "John"
  }
  
  fun sayHello() {
    println(name!!.uppercase())
  }
  
  initialize()
  sayHello()
}

main15()

println("Input Integer:")
fun readInt() = readLine()!!.toInt()

println("Input Integer:")
fun readInt2() = readLine()?.toInt()

fun readInt3(): Int? {
  println("Input Integer:")
  val tmp = readLine()
  
  return if (tmp != null) tmp.toInt() else null
}

println("Input Integer:")
println(readLine()?.toInt()?.toString(16))

println("Input Integer:")
fun readInt4() = readLine()?.toInt()

fun main16() {
  val n = readInt4() // Int?
  
  if (n != null) {
    println(n + 1)
  } else {
    println("No value")
  }
}

main16()

fun sayHello(name: String?) {
  println("Hello, " + (name ?: "Unknown"))
}

fun main17() {
  sayHello("John") // Hello, John
  sayHello(null)   // Hello, Unknown
}

main17()

fun sayHello2(name: String?) {
  println("Hello, " + (if (name != null) name else "Unknown"))
}

println("Input Integer:")
val n2 = readLine()?.toInt() ?: 0

class Name(val firstName: String, val familyName: String?)

class Person25(val name: Name?) {
  fun describe(): String {
    val currentName = name ?: return "Unknown"
    return "${currentName.firstName} ${currentName.familyName}"
  }
}

fun main18() {
  println(Person25(Name("John", "Doe")).describe()) // John Doe
  println(Person25(null).describe()) // Unknown
}

main18()

val prefix = "Hello, " // 최상위 불변 프로퍼티

fun main19() {
  println("Input Name:")
  val name = readLine() ?: return
  println("$prefix$name")
}

main19()

//import java.io.File

class Content {
  var text: String? = null
  
  fun loadFile(file: File) {
    text = file.readText()
  }
}

fun getContentSize(content: Content) = content.text?.length ?: 0


class Content2 {
  lateinit var text: String
  
  fun loadFile(file: File) {
    text = file.readText()
  }
}

fun getContentSize(content: Content2) = content.text.length

lateinit var text: String

fun readText() {
  println("Input Text:")
  text = readLine()!!
}

fun main20() {
  readText()
  println(text)
}

main20()

class Person26(val firstName: String, val familyName: String) {
  val fullName: String
    get(): String {
      return "$firstName $familyName"
    }
}

fun main21() {
  val person = Person26("John", "Doe")
  println(person.fullName) // John Doe
}

main21()


class Person27(val firstName: String, val familyName: String) {
  val fullName: String
    get() = "$firstName $familyName"
/*
  val fullName2: Any
    get(): String { 
	  // error: getter return type must be equal to the type of the property, i.e. 'Any'
      return "$firstName $familyName"
    }
*/	
  val fullName3
    get() = "$firstName $familyName" // 타입이 String으로 추론된다
}

class Person28(val firstName: String, val familyName: String, age: Int) {
  val age: Int = age
    get(): Int {
      println("Accessing age")
      return field
    }
}

class Person29(val firstName: String, val familyName: String) {
  var age: Int? = null
    set(value) {
      if (value != null && value <= 0) {
        throw IllegalArgumentException("Invalid age: $value")
      }
      field = value
    }
}

fun main22() {
  val person = Person29("John", "Doe")
  person.age = 20     // 커스텀 세터를 호출
  println(person.age) // 20   (커스텀 게터를 호출)
}

main22()

class Person30(var firstName: String, var familyName: String) {

  var fullName: String
    get(): String = "$firstName $familyName"
    set(value) {
      val names = value.split(" ") // 공백으로 구분해 단어를 분리한다
      if (names.size != 2) {
        throw IllegalArgumentException("Invalid full name: '$value'")
      }
      firstName = names[0]
      familyName = names[1]
    }
}

class Person31(name: String) {
  var lastChanged: Date? = null
    private set // Person 클래스 밖에서는 변경할 수 없다
  
  var name: String = name
    set(value) {
      lastChanged = Date()
      field = value
    }	
}

val text2 by lazy {
  File("data.txt").readText()
}

fun main23() {
  while (true) {
    println("Input 'print data' or 'exit':")
    when (val command = readLine() ?: return) {
      "print data" ->println(text2)
      "exit" -> return
    }
  }
}

main23()

// error: type 'Lazy<String>' has no method 'setValue(Chapter4, KProperty<*>, String)' and thus it cannot serve as a delegate for var (read-write property)
//var text3 by lazy { "Hello" }

fun longComputation(): Int { println("long computation done"); return 1 }

fun main24(args: Array<String>) {
  val data by lazy { longComputation() } // lazy 지역 변수
  val name = args.firstOrNull() ?: return
  println("$name: $data") // name이 널이 아닐 때만 data에 접근할 수 있음
}

main24(arrayOf("1","2","3"))

fun main25() {
  println("input text:")
  val data by lazy { readLine() }
  
  if (data != null) {
    // error: smart cast to 'String' is impossible, because 'data' is a property that has open or custom getter
    //println("Length: ${data.length}")
  }
}

main25()

object Application {
  val name = "My Application"
  
  override fun toString() = name
  
  fun exit() { }
}

fun describe(app: Application) = app.name // Application은 타입임

fun main26() {
  println(Application)                    // Application은 값임
}

main26()

class Application2 private constructor(val name: String) {
  object Factory {
    fun create(args: Array<String>): Application2? {
      val name = args.firstOrNull() ?: return null
      return Application2(name)
    }
  }
}

fun main27(args: Array<String>) {
  // 직접 생성자를 호출하도록 허용하지 않음
  // val app = Application2(name)
  val app = Application2.Factory.create(args) ?: return
  println("Application started: ${app.name}")
}

main27(arrayOf("test"))

class Application3 private constructor(val name: String) {
  companion object Factory {
    fun create(args: Array<String>): Application3? {
      val name = args.firstOrNull() ?: return null
      return Application3(name)
    }
  }
}

fun main28(args: Array<String>) {
  val app = Application3.create(args) ?: return
  println("Application started: ${app.name}")
}

main28(arrayOf("test"))

println(Application3.Factory.create(arrayOf("test2"))?.name)

class Application4 private constructor(val name: String) {
  companion object {
    fun create(args: Array<String>): Application4? {
      val name = args.firstOrNull() ?: return null
      return Application4(name)
    }
  }
}

class Application5 {
  companion object Factory
  //companion object Utils    // error: only one companion object is allowed per class
}

fun main29() {
  fun midPoint(xRange: IntRange, yRange: IntRange) = object {
    val x = (xRange.first + xRange.last)/2
    val y = (yRange.first + yRange.last)/2
  }
  
  val midPoint = midPoint(1..5, 2..6)
  
  println("${midPoint.x}, ${midPoint.y}") // (3, 4)
}

main29()

/*
fun printMiddle(xRange: IntRange, yRange: IntRange) {
  // error: named object 'MidPoint' is a singleton and cannot be local. Try to use anonymous object instead
  object MidPoint {
    val x = (xRange.first + xRange.last)/2
    val y = (yRange.first + yRange.last)/2
  }
  
  println("${MidPoint.x}, ${MidPoint.y}")
}
*/

fun main30() {
  val o = object {  // 익명 객체 타입이 추론됨
    init { println("input integer:") }
    val x = readLine()!!.toInt()
	init { println("input integer:") }
    val y = readLine()!!.toInt()
  }
  println(o.x + o.y) // 여기서 o안의 x와 y에 접근할 수 있음
} 

main30()

fun midPoint2(xRange: IntRange, yRange: IntRange) = object {
  val x = (xRange.first + xRange.last)/2
  val y = (yRange.first + yRange.last)/2
}

fun main31() {
  val midPoint = midPoint2(1..5, 2..6)
  // Error: x와 y를 찾을 수 없음
  //println("${midPoint.x}, ${midPoint.y}")
}

main31()

fun main32() {
  var x = 1
  
  val o = object {
    fun change() {
      x = 2
    }
  }
  
  o.change()
  println(x) // 2
}

main32()

fun main33() {
  var x = 1
  
  val o = object {
    val a = x++;
  }
  
  println("o.a = ${o.a}") // o.a = 1
  println("x = $x")       // x = 2
}

main33()

