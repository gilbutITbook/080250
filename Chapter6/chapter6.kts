import kotlin.random.Random

enum class WeekDay {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

fun WeekDay.isWorkDay() =
  this == WeekDay.SATURDAY || this == WeekDay.SUNDAY

fun main1() {
  println(WeekDay.MONDAY.isWorkDay())   // false
  println(WeekDay.SATURDAY.isWorkDay()) // true
}

main1()

/*
fun main2() {
  enum class Direction { NORTH, SOUTH, WEST, EAST } // Error
}
*/

enum class Direction {
  NORTH, SOUTH, WEST, EAST
}

fun rotateClockWise(direction: Direction) = when (direction) {
  Direction.NORTH -> Direction.EAST
  Direction.EAST -> Direction.SOUTH
  Direction.SOUTH -> Direction.WEST
  Direction.WEST -> Direction.NORTH
}

fun rotateClockWise2(direction: Direction) = when (direction) {
  Direction.NORTH -> Direction.EAST
  Direction.EAST -> Direction.SOUTH
  Direction.SOUTH -> Direction.WEST
  Direction.WEST -> Direction.NORTH
  else ->
    throw IllegalArgumentException("Invalid direction: $direction")
}

//$ kotlinc Direction.kt -include-runtime -d Direction.jar
//$ java -jar Direction.jar

enum class WeekDay2 {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
  
  val lowerCaseName get() = name.lowercase()
  fun isWorkDay() = this == SATURDAY || this == SUNDAY
}

fun main3() {
  println(WeekDay2.MONDAY.isWorkDay())      // false
  println(WeekDay2.WEDNESDAY.lowerCaseName) // wednesday
}

main3()

enum class RainbowColor(val isCold: Boolean) {
  RED(false), ORANGE(false), YELLOW(false),
  GREEN(true), BLUE(true), INDIGO(true), VIOLET(true);
  
  val isWarm get() = !isCold
}

fun main4() {
  println(RainbowColor.BLUE.isCold) // true
  println(RainbowColor.RED.isWarm)  // true
}

main4()

/*
enum class WeekDay3 {
  MONDAY { fun startWork() = println("Work week started") },
  TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

fun main5() = WeekDay3.MONDAY.startWork() // error: unresolved reference: startWork
*/

enum class Direction2 {
  NORTH, SOUTH, WEST, EAST;
}

fun main6() {
  println(Direction2.WEST.name)    // WEST
  println(Direction2.WEST.ordinal) // 2
}

main6()

fun main7() {
  println(Direction2.WEST == Direction2.NORTH) // false
  println(Direction2.WEST != Direction2.EAST) // true
  println(Direction2.EAST < Direction2.NORTH) // false
  println(Direction2.SOUTH >= Direction2.NORTH) // true
}

main7()

fun main8() {
  println(Direction2.valueOf("NORTH"))      // NORTH
  //java.lang.IllegalArgumentException: No enum constant NORTH_EAST
  //println(Direction2.valueOf("NORTH_EAST")) 
}

main8()

enum class WeekDay3 {
  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

private val weekDays = WeekDay3.values()

val WeekDay3.nextDay get() = weekDays[(ordinal + 1) % weekDays.size]

fun main9() {
  val weekDays = enumValues<WeekDay3>()
  
  println(weekDays[2])                       // WEDNESDAY
  println(enumValueOf<WeekDay3>("THURSDAY")) // THURSDAY
}

main9()

class Person1(val firstName: String,
             val familyName: String,
             val age: Int)

fun main10() {
  val person1 = Person1("John", "Doe", 25)
  val person2 = Person1("John", "Doe", 25)
  val person3 = person1

  println(person1 == person2) // false, 서로 다른 정체성
  println(person1 == person3) // true, 같은 정체성
}

main10()

data class Person2(val firstName: String,
                   val familyName: String,
                   val age: Int)

fun main11() {
  val person1 = Person2("John", "Doe", 25)
  val person2 = Person2("John", "Doe", 25)
  val person3 = person1

  println(person1 == person2) // true
  println(person1 == person3) // true
}

main11()

data class Person3(val firstName: String,
                   val familyName: String,
                   val age: Int)
                  
data class Mailbox1(val address: String, val person: Person3)

fun main12() {
  val box1 = Mailbox1("Unknown", Person3("John", "Doe", 25))
  val box2 = Mailbox1("Unknown", Person3("John", "Doe", 25))
  println(box1 == box2) // true
}

main12()

class Person4(val firstName: String,
              val familyName: String,
              val age: Int)
                  
data class Mailbox2(val address: String, val person: Person4)

fun main13() {
  val box1 = Mailbox2("Unknown", Person4("John", "Doe", 25))
  val box2 = Mailbox2("Unknown", Person4("John", "Doe", 25))
  // false: 두 Person 인스턴스의 정체성이 다름
  println(box1 == box2)
}

main13()

data class Person5(val firstName: String,
             val familyName: String,
             val age: Int)

fun main14() {
  val person = Person5("John", "Doe", 25)
  println(person) // Person(firstName=John, familyName=Doe, age=25)
}

main14()

data class Person6(val firstName: String, val familyName: String) {
  var age = 0
}

fun main15() {
  val person1 = Person6("John", "Doe").apply { age = 25 }
  val person2 = Person6("John", "Doe").apply { age = 26 }
  println(person1 == person2) // true
}

main15()

data class Person7(val firstName: String,
             val familyName: String,
             val age: Int)

fun Person7.show() = println("$firstName $familyName: $age")

fun main16() {
  val person = Person7("John", "Doe", 25)
  
  person.show()                                    // John Doe: 25
  person.copy().show()                             // John Doe: 25
  person.copy(familyName = "Smith").show()         // John Smith: 25
  person.copy(age = 30, firstName = "Jane").show() // Jane Doe: 30
}

main16()

fun main17() {
  val pair = Pair(1, "two")
  
  println(pair.first + 1)    // 2
  println("${pair.second}!") // two!
  
  val triple = Triple("one", 2, false)
  
  println("${triple.first}!") // one!
  println(triple.second - 1)  // 1
  println(!triple.third)      // true
}

main17()

val pair = 1 to "two"

println(pair.first + 1)    // 2
println("${pair.second}!") // two!

data class Person8(val firstName: String,
                  val familyName: String,
                  val age: Int)
                  
fun newPerson() = Person8(readLine()!!,
                         readLine()!!,
                         Random.nextInt(100))
fun newPersonWithPrompt(): Person8 {
  println("Input firstName and familyName(press enter after each name):")
  return newPerson()
}						 
                         
fun main18() {
  val person = newPersonWithPrompt()
  val firstName = person.firstName
  val familyName = person.familyName
  val age = person.age
  
  if (age < 18) {
    println("$firstName $familyName is under-age")
  }
}

main18()

fun main19() {
  val person = newPersonWithPrompt()
  val (firstName, familyName, age) = person
  
  
  if (age < 18) {
    println("$firstName $familyName is under-age")
  }
}

main19()


val (firstName2, familyName2: String, age2) = Person8("John", "Doe", 25)

val (firstName3, familyName3) = Person8("John", "Doe", 25)
println("$firstName3 $familyName3") // John Doe

val (name) = Person8("John", "Doe", 25)
println(name)                     // John

val (_, familyName4) = Person8("John", "Doe", 25)
println(familyName4) // Doe

var (firstName5, familyName5) = Person8("John", "Doe", 25)
firstName5 = firstName5.lowercase()
familyName5 = familyName5.lowercase()
println("$firstName5 $familyName5") // john doe

val pairs = arrayOf(1 to "one", 2 to "two", 3 to "three")

for ((number, name2) in pairs) {
  println("$number: $name2")
}

fun combine(person1: Person8,
            person2: Person8,
            folder: ((String, Person8) -> String)): String {
  return folder(folder("", person1), person2)
}

fun main20() {
  val p1 = Person8("John", "Doe", 25)
  val p2 = Person8("Jane", "Doe", 26)
  
  // 구조분해를 쓰지 않음
  println(combine(p1, p2) { text, person -> "$text ${person.age}" })
  
  // 구조분해를 씀
  println(combine(p1, p2) { text, (firstName) -> "$text $firstName" })
  println(combine(p1, p2) { text, (_, familyName) -> "$text $familyName" })
}

main20()

/*
data class Person9(val firstName: String,
                   val familyName: String,
                   val age: Int)
                  
data class Mailbox3(val address: String, val person: Person)

fun main21() {
  val (address, (firstName, familyName, age)) =
        Mailbox3("Unknown", Person9("John", "Doe", 25)) // Error
}

main21()
*/

@JvmInline
value class Dollar(val amount: Int) {
  fun add(d: Dollar) = Dollar(amount + d.amount)
  val isDebt get() = amount < 0
}

fun main22() {
  println(Dollar(15).add(Dollar(20)).amount) // 35
  println(Dollar(-100).isDebt) // true
}

main22()

fun safeAmount(dollar: Dollar?) = dollar?.amount ?: 0

fun main23() {
  println(Dollar(15).amount)      // 인라이닝됨
  println(Dollar(15))             // Any?로 사용되기 때문에 인라이닝되지 않음
  println(safeAmount(Dollar(15))) // Dollar?로 사용되기 때문에 인라이닝되지 않음
}

main23()


val uByte: UByte = 1u     // 명시적으로 UByte
val uShort: UShort = 100u // 명시적으로 UShort
val uInt = 1000u          // 자동으로 UInt로 추론
val uLong: ULong = 1000u  // 명시적으로 ULong
val uLong2 = 1000uL       // L 접미사가 붙었기 때문에 명시적으로 ULong

//error: the integer literal does not conform to the expected type Long
//val long: Long = 1000uL

println(1.toUByte())       // 1, Int -> UByte
println((-100).toUShort()) // 65436, Int -> UShort
println(200u.toByte())     // -56, UInt -> Byte
println(1000uL.toInt())    // 1000, ULong -> Int

println(1u + 2u) // 3
println(1u - 2u) // 4294967295
println(3u * 2u) // 6
println(5u / 2u) // 2
println(7u % 3u) // 1

/*
//error: conversion of signed constants to unsigned ones is prohibited
println(1u + 2)
println(1 + 2u)

// error: unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
println(-1u) // Error
*/

var uInt2: UInt = 1u
++uInt2
uInt2 -= 3u

val ua: UByte = 67u  // 01000011
val ub: UByte = 139u // 10001011
println(ua.inv())    // 10111100: 188
println(ua or ub)    // 11001011: 203
println(ua xor ub)   // 11001000: 200
println(ua and ub)   // 00000011: 3

val ua2 = 67u      // 0..0001000011
println(ua2 shr 2) // 0..0000010000: 16
println(ua2 shl 2) // 0..0100001100: 268

println(1u < 2u)            // true
println(2u >= 3u)           // false
println(2u + 2u == 1u + 3u) // true

@ExperimentalUnsignedTypes
val uBytes = ubyteArrayOf(1u, 2u, 3u)
@ExperimentalUnsignedTypes
val squares = UIntArray(10) { it.toUInt()*it.toUInt() }

println((1u .. 10u).joinToString())            // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
println((1u .. 10u step 2).joinToString())     // 1, 3, 5, 7, 9
println((1u until 10u).joinToString())         // 1, 2, 3, 4, 5, 6, 7, 8, 9
println((10u downTo 1u).joinToString())        // 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
println((10u downTo 1u step 3).joinToString()) // 10, 7, 4, 1





