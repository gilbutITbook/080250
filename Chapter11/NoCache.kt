import kotlin.reflect.*

@Target(AnnotationTarget.PROPERTY)
annotation class NoCache

class CachedPropertyProvider<in R, out T : Any>(
  val initializer: R.() -> T
) {
  operator fun provideDelegate(
    receiver: R?,
    property: KProperty<*>
  ): CachedProperty<R, T> {
    if (property.annotations.any{ it is NoCache }) {
      throw IllegalStateException("${property.name} forbids caching")
    }
    return CachedProperty(initializer)
  }
}

class CachedProperty<in R, out T : Any>(val initializer: R.() -> T) {
  private val cachedValues = HashMap<R, T>()
  operator fun getValue(receiver: R, property: KProperty<*>): T {
    return cachedValues.getOrPut(receiver) { receiver.initializer() }
  }
}

fun <R, T : Any> cached(initializer: R.() -> T) =
  CachedPropertyProvider(initializer)

class Person(val firstName: String, val familyName: String)

@NoCache val Person.fullName: String by cached {
    if (this != null) "$firstName $familyName" else ""
}

fun main() {
  val johnDoe = Person("John", "Doe")
  println(johnDoe.fullName) // Exception
}
