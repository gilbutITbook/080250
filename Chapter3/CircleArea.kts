import kotlin.math.PI

fun circleArea(radius: Double): Double {
    return PI*radius*radius
}

fun main() {
    print("Enter radius: ")
    val radius = readLine()!!.toDouble()
    println("Circle area: ${circleArea(radius)}")
}

main()