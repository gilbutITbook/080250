fun rectangleArea(width: Double, height: Double): Double {
  return width*height
}

fun main() {
  val w = readLine()!!.toDouble()
  val h = readLine()!!.toDouble()
  println("Rectangle area: ${rectangleArea(w, h)}")
  
  rectangleArea(width = w, height = h)
  rectangleArea(height = h, width = w)
}

main()
