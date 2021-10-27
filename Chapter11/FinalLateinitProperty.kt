import kotlin.reflect.KProperty

class FinalLateinitProperty<in R, T : Any> {
  private lateinit var value: T
  operator fun getValue(receiver: R, property: KProperty<*>): T {
    return value
  }
  operator fun setValue(receiver: R,
                        property: KProperty<*>,
                        newValue: T) {
    if (this::value.isInitialized) throw IllegalStateException(
          "Property ${property.name} is already initialized"
        )
      value = newValue
  }
}

fun <R, T : Any> finalLateInit() = FinalLateinitProperty<R, T>()

var message: String by finalLateInit()

fun main() {
  message = "Hello"
  println(message) // Hello
  message = "Bye"  // Exception: Property message is already initialized
}
