fun max(a: Int, b: Int) = if (a > b) a else b

fun main() {
  val s = readLine()!!
  val i = s.indexOf("/")
  
  // 10/3을 10과 3으로 나눠서 나눗셈을 수행한다
  val result = if (i>= 0) {
    val a = s.substring(0, i).toInt()
    val b = s.substring(i + 1).toInt()
    (a/b).toString()
  } else ""
  
  println(result)
}

main()