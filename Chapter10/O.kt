object O {
  val text = "Singleton"
}

fun main() {
  println(O::class.objectInstance!!.text) // Singleton
}
