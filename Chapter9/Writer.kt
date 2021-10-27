class Writer<in T> {
  // 함수 인자로 쓰임
  fun write(value: T) {
    println(value)
  }
  
  // in 위치에 사용된 Iterable 제네릭 타입의 out 위치 인자로 T를 사용함 
  // 이런 경우 위치가 in 위치로 인정됨
  fun writeList(values: Iterable<T>) {
    values.forEach { println(it) }
  }
}

fun main() {
  val numberWriter = Writer<Number>()
  
  // 맞음: Writer<Number>가 Int도 처리 가능
  val integerWriter: Writer<Int> = numberWriter
  
  integerWriter.write(100)
}
