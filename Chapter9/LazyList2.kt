interface LazyList<out T> {
  // 반환 타입으로 쓰임
  fun get(index: Int): T

  // 반환 타입의 out 타입 파라미터로 쓰임
  fun subList(range: IntRange): LazyList<T>

  // error: type parameter T is declared as 'out' but occurs in 'invariant' position in type () -> Array<T>
  fun getUpTo(index: Int): () -> Array<T>
}
