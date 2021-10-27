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

operator fun Rational.plus(n: Int) = this + Rational.of(n)

operator fun Int.plus(r: Rational) = r + this

operator fun Rational.minus(n: Int) = this - Rational.of(n)

operator fun Int.minus(r: Rational) = Rational.of(this) - r

private fun Rational.isLessOrEqual(r: Rational): Boolean {
  return sign*num*r.den <= r.sign*r.num*den
}

class RationalRange(val from: Rational, val to: Rational) {
  override fun toString() = "[$from, $to]"
  
  operator fun contains(r: Rational): Boolean {
    return from.isLessOrEqual(r) &&r.isLessOrEqual(to)
  }
  
  operator fun contains(n: Int) = contains(r(n))
}

operator fun Rational.rangeTo(r: Rational) = RationalRange(this, r)

fun main() {
  // 1/2 in [1/4, 1]
  println(r(1, 2) in r(1, 4)..r(1)) // true
  
  // 1 not in [5/4, 7/4]
  println(1 !in r(5, 4)..r(7, 4))   // true
}
