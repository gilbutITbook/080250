import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.vetoable
import java.io.File
import kotlin.properties.Delegates.observable

operator fun String.times(n: Int) = repeat(n)

println("abc" * 3) // abcabcabc

println("abc".times(3)) // abcabcabc

val x = 1.plus(2) // 1 + 2 와 같음

enum class Color {
   BLACK, RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE;

  operator fun not() = when (this) {
    BLACK -> WHITE
    RED -> CYAN
    GREEN -> MAGENTA
    BLUE -> YELLOW
    YELLOW -> BLUE
    CYAN -> RED
    MAGENTA -> GREEN
    WHITE -> BLACK
  }
}

fun main1() {
  println(!Color.RED) // CYAN
  println(!Color.CYAN) // RED
}

main1()

//$ kotlinc IsUpperCase.kt -include-runtime -d IsUpperCase.jar
//$ java -jar IsUpperCase.jar

enum class RainbowColor {
  RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;
  operator fun inc() = values[(ordinal + 1) % values.size]
  operator fun dec() = values[(ordinal + values.size - 1) % values.size]
  companion object {
    private val values = enumValues<RainbowColor>()
  }
}

var color = RainbowColor.INDIGO
println(color++)

var color2 = RainbowColor.INDIGO
val _oldColor = color2
color2 = color2.inc()
println(_oldColor) // INDIGO

var color3 = RainbowColor.INDIGO
println(color3++)

var color4 = RainbowColor.INDIGO
color4 = color4.inc()
println(color4) // VIOLET

//$ kotlinc Rational.kt -include-runtime -d Rational.jar
//$ java -jar Rational.jar


//$ kotlinc Rational2.kt -include-runtime -d Rational2.jar
//$ java -jar Rational2.jar

//$ kotlinc Rational3.kt -include-runtime -d Rational3.jar
//$ java -jar Rational3.jar

//$ kotlinc Rational4.kt -include-runtime -d Rational4.jar
//$ java -jar Rational4.jar

//$ kotlinc Rational5.kt -include-runtime -d Rational5.jar
//$ java -jar Rational5.jar

//$ kotlinc Infix.kt -include-runtime -d Infix.jar
//$ java -jar Infix.jar

var numbers = listOf(1, 2, 3)
numbers += 4
println(numbers) // [1, 2, 3, 4]


val numbers2 = mutableListOf(1, 2, 3)
numbers2 += 4
println(numbers2) // [1, 2, 3, 4]

//$ kotlinc AugAssign.kt -include-runtime -d AugAssign.jar
//$ java -jar AugAssign.jar

//$ kotlinc TreeNode.kt -include-runtime -d TreeNode.jar
//$ java -jar TreeNode.jar

operator fun <K, V> Map<K, V>.invoke(key: K) = get(key)

val m = mapOf("I" to 1, "V" to 5, "X" to 10)
println(m("V")) // 5
println(m("L")) // null

//$ kotlinc Rational6.kt -include-runtime -d Rational6.jar
//$ java -jar Rational6.jar

val array = arrayOf(1, 2, 3)
println(array[0]) // println(array.get(0))와 같음


val array2 = arrayOf(1, 2, 3)
array2[0] = 10 // array2.set(0, 10)과 같음

//$ kotlinc TreeNode2.kt -include-runtime -d TreeNode2.jar
//$ java -jar TreeNode2.jar

//$ kotlinc Rational7.kt -include-runtime -d Rational7.jar
//$ java -jar Rational7.jar

val map2 = mapOf("I" to 1, "V" to 5, "X" to 10)
for ((key, value) in map2) {
  println("$key = $value")
}

val numbers3 = listOf(10, 20, 30, 40, 50)
val (a, b, c) = numbers3
println("$a, $b, $c") // 10, 20, 30


//$ kotlinc TreeNode3.kt -include-runtime -d TreeNode3.jar
//$ java -jar TreeNode3.jar

val result by lazy { 1 + 2 }

val text by lazy { File("README.md").readText() }

private val lock = Any()
val text2 by lazy(lock) { File("README.md").readText() }

val myValue by lazy {
  println("Initializing myValue")
  123
}

val myValue2 by lazy(LazyThreadSafetyMode.PUBLICATION) {
   println("Initializing myValue")
   123
}

fun main2() {
  val x by lazy(LazyThreadSafetyMode.NONE) { 1 + 2 }
  println(x) // 3
}


// import kotlin.properties.Delegates.notNull // 맨 앞에서 임포트함

var text3: String by notNull()
fun readText() {
  println("Input a text and press endter: ")
  text3 = readLine()!!
}

fun main3() {
  readText()
  println(text3)
}

main3()

// import kotlin.properties.Delegates.notNull // 맨 앞에서 임포트함

var num: Int by notNull() // lateinit을 쓸 수 없음

fun main4() {
  num = 10
  println(num) // 10
}

main4()

//import kotlin.properties.Delegates.observable  // 파일 맨 앞에서 임포트함

class Person(name: String, val age: Int) {
  var name: String by observable(name) { property, old, new ->
    println("Name changed: $old to $new")
  }
}

fun main5() {
  val person = Person("John", 25)
  
  person.name = "Harry"   // Name changed: John to Harry
  person.name = "Vincent" // Name changed: Harry to Vincent
  person.name = "Vincent" // Name changed: Vincent to Vincent
}

main5()

//import kotlin.properties.Delegates.vetoable 파일 맨 앞에서 임포트

var password: String by vetoable("password") { _, _, new ->
  if (new.length< 8) {
    println("Password should be at least 8 characters long")
    false
  } else {
    println("Password is Ok")
    true
  }
}

fun main6() {
  password = "pAsSwOrD" // Password is Ok
  password = "qwerty"   // Password should be at least 8 characters long 표시됨
}

main6()

class CartItem(data: Map<String, Any?>) {
  val title: String by data
  val price: Double by data
  val quantity: Int by data
}

fun main7() {
  val item = CartItem(mapOf(
    "title" to "Laptop",
    "price" to 999.9,
    "quantity" to 1
  ))
  
  println(item.title)    // Laptop
  println(item.price)    // 999.9
  println(item.quantity) // 1
}

main7()

class MutableCartItem(data: MutableMap<String, Any?>) {
  var title: String by data
  var price: Double by data
  var quantity: Int by data
}

fun main8() {
  val item = MutableCartItem(mutableMapOf(
    "title" to "Laptop",
    "price" to 999.9,
    "quantity" to 1
  ))
  
  println(item.title)    // Laptop
  println(item.price)    // 999.9
  println(item.quantity) // 1
  item.title = "My Laptop"
  println(item.title)    // My Laptop
}

main8()

//$ kotlinc Delegate.kt -include-runtime -d Delegate.jar
//$ java -jar Delegate.jar


//$ kotlinc FinalLateinitProperty.kt -include-runtime -d FinalLateinitProperty.jar
//$ java -jar FinalLateinitProperty.jar

//$ kotlinc NoCache.kt -include-runtime -d NoCache.jar
//$ java -jar NoCache.jar

//$ kotlinc DelegateReflection.kt -include-runtime -d DelegateReflection.jar
//$ java -jar DelegateReflection.jar

//$ kotlinc CachedReflection.kt -include-runtime -d CachedReflection.jar
//$ java -jar CachedReflection.jar
