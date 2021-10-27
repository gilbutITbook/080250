import kotlin.reflect.*
import kotlin.reflect.full.getExtensionDelegate
import kotlin.reflect.jvm.isAccessible
 
class Person(val firstName: String, val familyName: String)

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

val Person.fullName: String by cached { "$firstName $familyName" }

fun main() {
  println(
    Person::fullName
      .apply { isAccessible = true }
      .getExtensionDelegate()!!::class.qualifiedName
  ) // CachedProperty
}
