fun swap(s: String, from: Int, to: Int): String {
  val chars = s.toCharArray() // 배열로 변환
  // 배열 원소 교환하기
  val tmp = chars[from]
  chars[from] = chars[to]
  chars[to] = tmp
  return chars.concatToString() // 문자열로 다시 변환
}

fun main() {
  println(swap("Hello", 1, 2)) // Hlelo
  println(swap("Hello", from = 1, to = 2)) // Hlelo
  println(swap("Hello", to = 3, from = 0)) // lelHo
  println(swap("Hello", 1, to = 3)) // Hlleo
  println(swap(from = 1, s = "Hello", to = 2)) // Hlelo
  // 위치 기반 인자와 이름 붙은 인자를 혼용한 경우
  println(swap(s = "Hello", 1, 2))      // 1.4 이전에는 컴파일 오류. 1.4부터는 정상
  println(swap(s = "Hello", 1, to = 2)) // 1.4 이전에는 컴파일 오류. 1.4부터는 정상
  /*
  println(swap(s = "Hello", 2, from = 1)) // error: an argument is already passed for this parameter
                                          // error: no value passed for parameter 'to'
  println(swap(1, 2, s = "Hello"))        // the integer literal does not conform to the expected type String
                                          // error: an argument is already passed for this parameter
                                          //error: no value passed for parameter 'to'
  */										  
}

main()