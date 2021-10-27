interface List<out T> {
  val size: Int
  fun get(index: Int): T
}

interface MutableList<T> : List<T> {
  fun set(index: Int, value: T)
}
