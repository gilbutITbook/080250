import kotlin.math.abs

class Rational private constructor(
  val sign: Int,
  val num: Int,
  val den: Int
) {
  operator fun unaryMinus() = Rational(-sign, num, den)
  
  operator fun plus(r: Rational): Rational {
    val gcd = gcd(den, r.den)
    val newDen = den/gcd*r.den
    val newNum = newDen/den*num*sign + newDen/r.den*r.num*r.sign
    val newSign = newNum.sign()
    
    return Rational(newSign, abs(newNum), newDen)
  }
  
  operator fun minus(r: Rational) = this + (-r)
  
  operator fun times(r: Rational): Rational {
    return of(sign*r.sign*num*r.num, den*r.den)
  }
  
  operator fun div(r: Rational): Rational {
    return of(sign*r.sign*num*r.den, den*r.num)
  }
  
  override fun toString(): String {
    return "${sign*num}" + if (den != 1) "/$den" else ""
  }
  
  companion object {
    private fun Int.sign() = when {
      this > 0 -> 1
      this < 0 -> -1
      else -> 0
    }
    
    private tailrec fun gcd(a: Int, b: Int): Int {
      return if (b == 0) a else gcd(b, a % b)
    }
    
    fun of(num: Int, den: Int = 1): Rational {
      if (den == 0) throw ArithmeticException("Denominator is zero")
      
      val sign = num.sign() * den.sign()
      val numAbs = abs(num)
      val denAbs = abs(den)
      val gcd = gcd(numAbs, denAbs)
      
      return Rational(sign, numAbs/gcd, denAbs/gcd)
    }
  }
}

fun r(num: Int, den: Int = 1) = Rational.of(num, den)

fun main() {
  // 1/2 - 1/3
  println(r(1, 2) - r(1, 3))       // 1/6
  
  // 2/3 + (1/3)/2
  println(r(2, 3) + r(1, 3)/r(2))  // 5/6
  
  // 3/4 * 8/9 / (2/3)
  println(r(3, 4)*r(8, 9)/r(2, 3)) // 1
  
  // (1/10)*2 - 2/6
  println(r(1, 10)*r(2) - r(2, 6)) // -2/15
}
