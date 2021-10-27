interface MyMap<K : Any, out V>

fun main() {
  val parameters = MyMap::class.typeParameters
  // K: [kotlin.Any], V: [kotlin.Any?]
  println(parameters.joinToString { "${it.name}: ${it.upperBounds}" })
}
