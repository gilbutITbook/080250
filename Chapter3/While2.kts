import kotlin.random.*

fun main() {
  val num = Random.nextInt(1, 101)
  var guess = 0
  
  while (guess != num) {
    guess = readLine()!!.toInt()
    if (guess < num) println("Too small")
    else if (guess > num) println("Too big")
  }
  println("Right: it's $num")
}

main()
