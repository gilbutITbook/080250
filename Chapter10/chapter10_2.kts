import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

annotation class Dependency(vararg val componentClasses: KClass<*>)

annotation class Component(
  val name: String = "Core",
  val dependency: Dependency = Dependency()
)

@Component("I/O")
class IO

@Component("Log", Dependency(IO::class))
class Logger

@Component(dependency = Dependency(IO::class, Logger::class))
class Main

fun main() {
  val component = Main::class.annotations
    .filterIsInstance<Component>()
    .firstOrNull() ?: return
  
  println("Component name: ${component.name}")
  
  val depText = component.dependency.componentClasses
    .joinToString { it.simpleName ?: "" }
  
  println("Dependencies: $depText")
}

main()

inline fun <reified T> Any.cast() = this as? T

val obj: Any = "Hello"
println(obj.cast<String>())

println((1 + 2)::class) // class kotlin.Int
println("abc"::class)   // class kotlin.String

val stringClass = Class.forName("java.lang.String").kotlin
println(stringClass.isInstance("Hello")) // true

println(String::class.java) // class java.lang.String

println(Any::class.qualifiedName) // kotlin.Any
println(Any::class.jvmName)       // java.lang.Object

println(String::class.isInstance(""))   // true
println(String::class.isInstance(12))   // false
println(String::class.isInstance(null)) // false


//$ kotlinc Person.kt -include-runtime -d Person.jar
//$ java -jar Person.jar

//$ kotlinc O.kt -include-runtime -d O.jar
//$ java -jar O.jar

//$ kotlinc Parent.kt -include-runtime -d Parent.jar
//$ java -jar Parent.jar

//$ kotlinc MyMap.kt -include-runtime -d MyMap.jar
//$ java -jar MyMap.jar

fun combine(n: Int, s: String) = "$s$n"

fun main2() {
  println(::combine.returnType) // kotlin.String
}

main2()


//$ kotlinc MemberExtension.kt -include-runtime -d MemberExtension.jar
//$ java -jar MemberExtension.jar

class Person(val firstName: String, val familyName: String) {
  fun fullName(familyFirst: Boolean): String = if (familyFirst) {
      "$familyName $firstName"
    } else {
      "$firstName $familyName"
    }
}

fun main3() {
  val person = Person("John", "Doe")
  val personClass = person::class
  val firstName = personClass.members.first { it.name == "firstName" }
  
  println(firstName.call(person)) // John
}

main3()

val myValue = 1

fun main4() {
  println(::myValue.getter()) // 1
}

main4()

//$ kotlinc KFunction.kt -include-runtime -d KFunction.jar
//$ java -jar KFunction.jar

//$ kotlinc Accessible.kt -include-runtime -d Accessible.jar
//$ java -jar Accessible.jar
