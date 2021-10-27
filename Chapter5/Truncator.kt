interface Truncator {
  val truncated: String
  val original: String
} 

fun String.truncator(max: Int) = object:Truncator  {
  override val truncated
    get() = if (length <= max) this@truncator else substring(0, max)
   
  override val original
    get() = this@truncator
}

fun main() {
  val truncator = "Hello".truncator(3)
  
  println(truncator.original) // Hello
  println(truncator.truncated) // Hel
}