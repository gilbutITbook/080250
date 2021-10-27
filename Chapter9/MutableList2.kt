interface List<out T> {
  val size: Int
  fun get(index: Int): T
}

interface MutableList<out T> : List<T> {
  // error: type parameter T is declared as 'out' but occurs in 'in' position in type T 
  fun set(index: Int, value: T)
}
