import kotlin.random.*

fun main() {
  val num = Random.nextInt(1, 101)

  while (true) {
    val guess = readLine()!!.toInt()
  
    if (guess < num) println("Too small")
    else if (guess > num) println("Too big")
    else break
  }
  
  println("Right: it's $num")
}

main()