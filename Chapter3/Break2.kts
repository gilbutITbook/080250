import kotlin.random.*

fun main() {
  val num = Random.nextInt(1, 101)
  
  while (true) {
    val guess = readLine()!!.toInt()
    val message =
      if (guess < num) "Too small"
      else if (guess > num) "Too big"
      else break
    println(message)
  }
  
  println("Right: it's $num")
}

main()