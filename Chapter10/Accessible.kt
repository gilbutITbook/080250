import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

class SecretHolder(private val secret: String)

fun main() {
  val secretHolder = SecretHolder("Secret")
  val secretProperty = secretHolder::class.members
    .first { it.name == "secret" } as KProperty1<SecretHolder, String>
    
  secretProperty.isAccessible = true
  println(secretProperty.get(secretHolder))
}
