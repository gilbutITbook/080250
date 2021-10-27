interface LazyList<out T> {
  // 반환 타입으로 쓰임
  fun get(index: Int): T

  // 반환 타입의 out 타입 파라미터로 쓰임
  fun subList(range: IntRange): LazyList<T>

  // 함수 타입의 반환값 부분도 out 위치임
  fun getUpTo(index: Int): () -> List<T>
}
