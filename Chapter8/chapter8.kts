import kotlin.math.PI

open class Vehicle {
  var currentSpeed = 0
  
  fun start() {
    println("I’m moving")
  }
  
  fun stop() {
    println("Stopped")
  }
}

open class FlyingVehicle : Vehicle() {
  fun takeOff() {
    println("Taking off")
  }
  fun land() {
    println("Landed")
  }
}

class Aircraft(val seats: Int) : FlyingVehicle()

// error: this type is final, so it cannot be inherited from
//class Airbus(seats: Int) : Aircraft(seats) 

val aircraft = Aircraft(100)
val vehicle: Vehicle = aircraft // 상위 타입으로 암시적으로 변환
vehicle.start()                 // Vehicle의 메서드 호출
vehicle.stop()                  // Vehicle의 메서드 호출
aircraft.start()                // Vehicle의 메서드 호출
aircraft.takeOff()              // FlyingVehicle의 메서드 호출
aircraft.land()                 // FlyingVehicle의 메서드 호출
aircraft.stop()                 // Vehicle의 메서드 호출
println(aircraft.seats)         // Aircraft 자체 프로퍼티 접근

//open data class Person(val name: String,val age: Int) // error: modifier 'open' is incompatible with 'data'

//class MyBase
//open value class MyString(val value: String) // error: inline classes can be only final
//value class MyStringInherited(val value: String): MyBase() // error: inline class cannot extend classes

open class Person(val name: String, val age: Int) {
  companion object : Person("Unknown", 0)
}

object JohnDoe : Person("John Doe", 30)

open class Vehicle2 {
  open fun start() {
    println("I’m moving")
  }
  fun stop() {
    println("Stopped")
    }
}

class Car : Vehicle2() {
  override fun start() {
    println("I'm riding")
  }
}

class Boat : Vehicle2() {
  override fun start() {
    println("I'm sailing")
  }
}

fun startAndStop(vehicle: Vehicle2) {
  vehicle.start()
  vehicle.stop()
}

fun main1() {
  startAndStop(Car())
  startAndStop(Boat())
}

main1()

open class Vehicle3 {
  open fun start() {
    println("I'm moving")
  }
}

fun Vehicle3.stop() {
  println("Stopped moving")
}

class Car2 : Vehicle3() {
  override fun start() {
    println("I'm riding")
  }
}

fun Car2.stop() {
  println("Stopped riding")
}

fun main2() {
  val vehicle: Vehicle3 = Car2()
  vehicle.start() // I’m riding
  vehicle.stop() // Stopped moving
}

main2()

/*
open class Vehicle4 {
  open fun start(speed: Int) {
    println("I’m moving at $speed")
  }
}

class Car3 : Vehicle4() {
  // 시그니처가 달라서 다른 메서드를 오버라이딩하는 것으로 인식됨
  override fun start() { // error: 'start' overrides nothing
    println("I'm riding")
  }
}*/

open class Vehicle5 {
  open fun start(): String? = null
}

open class Car4 : Vehicle5() {
  final override fun start() = "I'm riding a car"
}

open class Vehicle6 {
  open fun start() {
    println("I’m moving")
  }
}

open class Car5 : Vehicle6() {
  final override fun start() {
    println("I'm riding a car")
  }
}

/*
class Bus : Car5() {
  // error: 'start' in 'Car' is final and cannot be overridden
  override fun start() {
    println("I'm riding a bus")
  }
}
*/

open class Entity {
  open val name: String get() = ""
}

class Person2(override val name: String) : Entity()

open class Entity2 {
  open val name: String get() = ""
}

class Person3() : Entity2() {
  override var name: String = ""
}


open class Vehicle7 {
  protected open fun onStart() { }
  fun start() {
    println("Starting up...")
    onStart()
  }
}

class Car6 : Vehicle7() {
  override fun onStart() {
    println("It's a car")
  }
}

fun main3() {
  val car = Car6()
  car.start()    // Ok
  // error: cannot access 'onStart': it is protected in 'Car'
  //car.onStart()
}

main3()

open class Vehicle8 {
  init {
    println("Initializing Vehicle")
  }
}

open class Car7 : Vehicle8() {
  init {
    println("Initializing Car")
  }
}

class Truck : Car7() {
  init {
    println("Initializing Truck")
  }
}

fun main4() {
  Truck()
}

main4()

open class Person4(val name: String, val age: Int)

class Student(name: String, age: Int, val university: String) :
    Person4(name, age)

fun main5() {
  Student("Euan Reynolds", 25, "MIT")
}

main5()

open class Person5 {
  val name: String
  val age: Int
  
  constructor(name: String, age: Int) {
    this.name = name
    this.age = age
  }
}

class Student2(name: String, age: Int, val university: String) :
    Person5(name, age)

open class Person6(val name: String, val age: Int)

class Student3 : Person6 {
  val university: String
  constructor(name: String, age: Int, university: String) :
    super(name, age) {
    this.university = university
  }
}

/*
open class Person7(val name: String, val age: Int)

// Error: call to Person constructor is expected
class Student4() : Person7 {
  val university: String
  
  constructor(name: String, age: Int, university: String) :
      super(name, age) {         // error: primary constructor call expected
    this.university = university
  }
}
*/

open class Person8 {
  val name: String
  val age: Int
  
  constructor(name: String, age: Int) {
    this.name = name
    this.age = age
  }
  
  constructor(firstName: String, familyName: String, age: Int) :
      this("$firstName $familyName", age) {
  }
}

class Student5 : Person8 {
  val university: String
  
  constructor(name: String, age: Int, university: String) :
      super(name, age) {
    this.university = university
  }
  
  constructor(
    firstName: String,
    familyName: String,
    age: Int,
    university: String
  ) :
      super(firstName, familyName, age) {
    this.university = university
  }
}

fun main6() {
  Student5("Euan", "Reynolds", 25, "MIT")
  Student5("Val Watts", 22, "ETHZ")
}

main6()

open class Person9(val name: String, val age: Int) {
  open fun showInfo() {
    println("$name, $age")
  }
  
  init {
    showInfo()
  }
}

class Student6(
  name: String,
  age: Int,
  val university: String
) : Person9(name, age) {
  override fun showInfo() {
    println("$name, $age (student at $university)")
  }
}

fun main7() {
  Student6("Euan Reynolds", 25, "MIT")
}

main7()

open class Person10(val name: String, val age: Int) {
  override fun toString() = "$name, $age"
  
  init {
    println(this) // 잠재적인 위험 요소
  }
}

class Student7(
  name: String,
  age: Int,
  val university: String
) : Person10(name, age) {
  override fun toString() = super.toString() + " (student at $university)"
}

fun main8() {
  // Euan Reynolds, 25 (student at null)
  Student7("Euan Reynolds", 25, "MIT")
}

main8()

val objects = arrayOf("1", 2, "3", 4)

//for (obj in objects) {
//  println(obj*2) // error: unresolved reference
//}

for (obj in objects) {
  println(obj is Int)
}

println(null is Int)     // false
println(null is String?) // true

//println(12 is String) // error: incompatible types: String and Int

val objects2 = arrayOf("1", 2, "3", 4)

var sum = 0

for (obj in objects2) {
  if (obj is Int) {
    sum += obj // 여기서는 obj의 타입을 `Int`로 세분화한다
  }
}
println(sum) // 6

val objects3 = arrayOf("1", 2, "3", 4)
var sum2 = 0

for (obj in objects3) {
  when (obj) {
    is Int -> sum2 += obj            // 여기서 obj는 Int 타입이다
    is String -> sum2 += obj.toInt() // 여기서 obj는 String 타입이다
  }
}
println(sum2) // 10

/*
class Holder {
  val o: Any get() = ""
}

fun main9() {
  val o: Any by lazy { 123 }
  
  if (o is Int) {
    println(o*2)             // error: smart cast to 'Int' is impossible
  }
  
  val holder = Holder()
  
  if (holder.o is String) {
    println(holder.o.length) // error: smart cast to 'String' is impossible
  }
}

main9()
*/

open class Holder2 {
  open val o: Any = ""
}

fun main10() {
  val holder = Holder2()
  if (holder.o is String) {
//    println(holder.o.length) // error: smart cast to 'String' is impossible
  }
}

main10()

fun main11() {
  var o: Any = 123
  if (o is Int) {
    println(o + 1)    // Ok: Int로 스마트 캐스트
    o = ""
    println(o.length) // Ok: String으로 스마트 캐스트
  }
  if (o is String) {
    val f = { o = 123 }
//    println(o.length) // error: smart cast to 'String' is impossible
  }
}

main11()

val o: Any = 123
println((o as Int) + 1)              // 124
println((o as? Int)!! + 1)           // 124
println((o as? String ?: "").length) // 0
//println((o as String).length)        // java.lang.ClassCastException

val o2: Any = 123
println(o2 as? String) // null
//println(o2 as String?) // java.lang.ClassCastException

//println(null as String) // java.lang.NullPointerException

class Address(
  val city: String,
  val street: String,
  val house: String
)
 
open class Entity4(
  val name: String,
  val address: Address
)
 
class Person11(
  name: String,
  address: Address,
  val age: Int
): Entity4(name, address)

class Organization(
  name: String,
  address: Address,
  val manager: Person11
) : Entity4(name, address)

fun main12() {
  val addresses = arrayOf(
    Address("London", "Ivy Lane", "8A"),
    Address("New York", "Kingsway West", "11/B"),
    Address("Sydney", "North Road", "129")
  )
  
  // -1
  println(addresses.indexOf(Address("Sydney", "North Road", "129")))
}

main12()


class Address2(
  val city: String,
  val street: String,
  val house: String
) {
  // Address에 정의한 equals 함수
  override fun equals(other: Any?): Boolean {
    if (other !is Address2) return false
    return city == other.city &&
      street == other.street &&
      house == other.house
  }
  
  // 위 equals와 호환이 되는 해시코드 정의
  override fun hashCode(): Int {
    var result = city.hashCode()
    result = 31 * result + street.hashCode()
    result = 31 * result + house.hashCode()
    return result
  }

}
 
open class Entity5(
  val name: String,
  val address: Address2
)
 
class Person12(
  name: String,
  address: Address2,
  val age: Int
): Entity5(name, address)

class Organization2(
  name: String,
  address: Address2,
  val manager: Person12
) : Entity5(name, address)

fun main13() {
  val addresses = arrayOf(
    Address2("London", "Ivy Lane", "8A"),
    Address2("New York", "Kingsway West", "11/B"),
    Address2("Sydney", "North Road", "129")
  )
  
  // -2
  println(addresses.indexOf(Address2("Sydney", "North Road", "129")))
}

main13()

val addr1 = Address2("London", "Ivy Lane", "8A")
val addr2 = addr1                               // 같은 인스턴스
val addr3 = Address2("London", "Ivy Lane", "8A") // 다른 인스턴스지만, 동등함
println(addr1 === addr2) // true
println(addr1 == addr2)  // true
println(addr1 === addr3) // false
println(addr1 == addr2)  // true

open class Entity6(
  val name: String,
  val address: Address2
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    
    other as Entity6
    if (name != other.name) return false
    if (address != other.address) return false
    
    return true
  }
  
  override fun hashCode(): Int {
    var result = name.hashCode()
    
    result = 31 * result + address.hashCode()
    
    return result
  }
}

class Person13(
  name: String,
  address: Address2,
  val age: Int
): Entity6(name, address) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false
    if (!super.equals(other)) return false
    
    other as Person13
    if (age != other.age) return false
    return true
  }
  
  override fun hashCode(): Int {
    var result = super.hashCode()
    result = 31 * result + age
    return result
  }
}

class Address3(
  val city: String,
  val street: String,
  val house: String
) {
  override fun toString() = "$city, $street, $house"
}

open class Entity7(
  val name: String,
  val address: Address3
)

class Person14(
  name: String,
  address: Address3,
  val age: Int
): Entity7(name, address) {
  override fun toString() = "$name, $age at $address"
}

class Organization3(
  name: String,
  address: Address3,
  val manager: Person14?
) : Entity7(name, address) {
  override fun toString() = "$name at $address"
}

fun main14() {
  // Euan Reynolds, 25 at London, Ivy Lane, 8A
  println(Person14("Euan Reynolds", Address3("London", "Ivy Lane", "8A"), 25))
  
  // Thriftocracy, Inc. at Perth, North Road, 129
  println(
    Organization3(
      "Thriftocracy, Inc.",
      Address3("Perth", "North Road", "129"),
      null
    )
  )
}

main14()

abstract class Entity8(val name: String)

// Ok: 하위 클래스에서 위임 호출
class Person15(name: String, val age: Int) : Entity8(name)

// error: cannot create an instance of an abstract class 
//val entity = Entity8("Unknown")


abstract class Entity9(val name: String)

class Person16 : Entity9 {
  constructor(name: String) : super(name)
  constructor(
    firstName: String,
    familyName: String
  ) : super("$firstName $familyName")
}

//import kotlin.math.PI // 스크립트 첫 부분에 복사함

abstract class Shape {
  abstract val width: Double
  abstract val height: Double
  abstract fun area(): Double
}

class Circle(val radius: Double) : Shape() {
  val diameter get() = 2*radius
  override val width get() = diameter
  override val height get() = diameter
  override fun area() = PI*radius*radius
}

class Rectangle(
  override val width: Double,
  override val height: Double
) : Shape() {
  override fun area() = width*height
}

fun Shape.print() {
  println("Bounds: $width*$height, area: ${area()}")
}

fun main15() {
  // Bounds: 20.0*20.0, area: 314.1592653589793
  Circle(10.0).print()
  
  // Bounds: 3.0*5.0, area: 15.0
  Rectangle(3.0, 5.0).print()
}

main15()

interface Vehicle9 {
  val currentSpeed: Int
  fun move()
  fun stop()
}

interface FlyingVehicle2 : Vehicle9 {
  val currentHeight: Int
  fun takeOff()
  fun land()
}

class Car8 : Vehicle9 {
  override var currentSpeed = 0
      private set
  
  override fun move() {
    println("Riding...")
    currentSpeed = 50
  }
  
  override fun stop() {
    println("Stopped")
    currentSpeed = 0
  }
}

class Aircraft2 : FlyingVehicle2 {
  override var currentSpeed = 0
      private set
  
  override var currentHeight = 0
      private set
  
  override fun move() {
    println("Taxiing...")
    currentSpeed = 50
  }
  
  override fun stop() {
    println("Stopped")
    currentSpeed = 0
  }
  
  override fun takeOff() {
    println("Taking off...")
    currentSpeed = 500
    currentHeight = 5000
  }
  
  override fun land() {
    println("Landed")
    currentSpeed = 50
    currentHeight = 0
  }
}


interface Vehicle10 {
  val currentSpeed: Int
  
  val isMoving get() = currentSpeed != 0
  
  fun move()
  
  fun stop()
  
  fun report() {
    println(if (isMoving) "Moving at $currentSpeed" else "Still")
  }
}

/*
interface Vehicle11 {
  // error: modifier 'final' is not applicable inside 'interface'
  final fun move() {}
}
*/

fun Vehicle10.relativeSpeed(vehicle: Vehicle10) =
  currentSpeed - vehicle.currentSpeed

interface Vehicle12 {
  fun move() {
    println("I'm moving")
  }
}

interface Car9 : Vehicle12 {
  override fun move() {
    println("I'm riding")
  }
}

/*
interface Vehicle13 {
  // error: property initializers are not allowed in interfaces
  val currentSpeed = 0
  // error: delegated properties are not allowed in interfaces
  val maxSpeed by lazy { 100 }
}
*/

interface Car10 {
  fun ride()
}

interface Aircraft3 {
  fun fly()
}

interface Ship {
  fun sail()
}

interface FlyingCar : Car10, Aircraft3

class Transformer :FlyingCar, Ship {
  override fun ride() {
    println("I'm riding")
  }
  override fun fly() {
    println("I'm flying")
  }
  override fun sail() {
    println("I'm sailing")
  }
}

interface Car11 {
  fun move()
}

interface Ship2 {
  fun move()
}

class Amphibia : Car11, Ship2 {
  override fun move() {
    println("I'm moving")
  }
}

interface Car12 {
  fun move(){
    println("I'm riding")
  }
}

interface Ship3 {
  fun move()
}

class Amphibia2 : Car12, Ship3 {
  override fun move() {
    super.move() // Car에서 상속받은 메서드를 호출
  }
}

fun main16() {
  Amphibia2().move() // I'm riding
}

interface Car13 {
  fun move(){
    println("I'm riding")
  }
}

interface Ship4 {
  fun move() {
    println("I'm sailing")
  }
}

class Amphibia3 : Car13, Ship4 {
  override fun move() {
    super<Car13>.move() // Car에서 상속받은 메서드를 호출
    super<Ship4>.move() // Ship에서 상속받은 메서드를 호출
  }
}

fun main17() {
  /*
      I'm riding
      I'm sailing
  */
  Amphibia3().move() 
}

main17()

enum class Result {
  SUCCESS, ERROR
}

fun runComputation(): Result {
  try {
    println("Input a int:")
    val a = readLine()?.toInt() ?: return Result.ERROR
	println("Input a int:")
    val b = readLine()?.toInt() ?: return Result.ERROR
    
    println("Sum: ${a + b}")
    
    return Result.SUCCESS
  } catch (e: NumberFormatException) {
    return Result.ERROR
  }
}

fun main18() {
  val message = when (runComputation()) {
    Result.SUCCESS -> "Completed successfully"
    Result.ERROR -> "Error!"
  }
  
  println(message)
}

main18()

abstract class Result2 {
  class Success(val value: Any) : Result2() {
    fun showResult() {
      println(value)
    }
  }
  
  class Error(val message: String) : Result2() {
    fun throwException() {
      throw Exception(message)
    }
  }
}

fun runComputation2(): Result2 {
  try {
    println("Input a int:")
    val a = readLine()?.toInt()
        ?: return Result2.Error("Missing first argument")
	println("Input a int:")	
    val b = readLine()?.toInt()
        ?: return Result2.Error("Missing second argument")
        
    return Result2.Success(a + b)
  } catch (e: NumberFormatException) {
    return Result2.Error(e.message ?: "Invalid input")
  }
}

fun main19() {
  val message = when (val result = runComputation2()) {
    is Result2.Success -> "Completed successfully: ${result.value}"
    is Result2.Error -> "Error: ${result.message}"
    else -> return
  }
  println(message)
}

main19()

sealed class Result3 {
  class Success(val value: Any) : Result3() {
    fun showResult() {
      println(value)
    }
  }
  
  class Error(val message: String) : Result3() {
    fun throwException() {
      throw Exception(message)
    }
  }
}

// error: sealed types cannot be instantiated
// val result = Result3() 

fun runComputation3(): Result3 {
  try {
    println("Input a int:")
    val a = readLine()?.toInt()
        ?: return Result3.Error("Missing first argument")
	println("Input a int:")	
    val b = readLine()?.toInt()
        ?: return Result3.Error("Missing second argument")
        
    return Result3.Success(a + b)
  } catch (e: NumberFormatException) {
    return Result3.Error(e.message ?: "Invalid input")
  }
}

val message = when (val result = runComputation3()) {
  is Result3.Success -> "Completed successfully: ${result.value}"
  is Result3.Error -> "Error: ${result.message}"
}

println(message) // 입력에 따라 다른 결과. 예: Completed successfully: 11

// $ kotlinc Util.kt Result.kt -include-runtime -d Result.jar

sealed class Result4

class Success(val value: Any) : Result4()

sealed class Error : Result4() {
  abstract val message: String
}

class ErrorWithException(val exception: Exception): Error() {
  override val message: String get() = exception.message ?: ""
}

class ErrorWithMessage(override val message: String): Error()

// $ kotlinc Eval.kt -include-runtime -d Eval.jar
// $ java -jar Eval.jar

interface PersonData {
  val name: String
  val age: Int
}

open class Person17(
  override val name: String,
  override val age: Int
): PersonData

data class Book(val title: String, val author: PersonData) {
  override fun toString() = "'$title' by ${author.name}"
}

fun main20() {
  val valWatts = Person17("Val Watts", 30)
  val introKotlin = Book("Introduction to Kotlin", valWatts)
  
  println(introKotlin) // 'Introduction to Kotlin' by Val Watts
}

main20()

class Alias(
  private val realIdentity: PersonData,
  private val newIdentity: PersonData
) :PersonData {
  override val name: String
    get() = newIdentity.name
    
  override val age: Int
    get() = newIdentity.age
}

fun main21() {
  val valWatts = Person17("Val Watts", 30)
  val johnDoe = Alias(valWatts, Person17("John Doe", 25))
  val introJava = Book("Introduction to Java",johnDoe)
  
  println(introJava) // 'Introduction to Java' by John Doe
}

main21()

class Alias2(
  private val realIdentity: PersonData,
  private val newIdentity: PersonData
) :PersonData by newIdentity {
  override val age: Int get() = realIdentity.age
}

fun main22() {
  val valWatts = Person17("Val Watts", 30)
  val johnDoe = Alias2(valWatts, Person17("John Doe", 25))
  println(johnDoe.age) // 30
}

main22()

class Alias3(
  private val realIdentity: PersonData,
  newIdentity: PersonData
) :PersonData by newIdentity

/*
class Alias4(
  private val realIdentity: PersonData
) :PersonData by newIdentity { // error: unresolved reference: newIdentity
  val newIdentity = Person17("John Doe", 30)
}*/

fun PersonData.aliased(newIdentity: PersonData) =
  object :PersonData by newIdentity {
    override val age: Int get() = this@aliased.age
  }

fun main23() {
  val valWatts = Person17("Val Watts", 30)
  val johnDoe = valWatts.aliased(Person17("John Doe", 25))
  
  println("${johnDoe.name}, ${johnDoe.age}") // John Doe, 30
}

main23()

class Alias4(
  private val realIdentity: PersonData,
  private val newIdentity: PersonData
) : Person by newIdentity // error: only interfaces can be delegated to



