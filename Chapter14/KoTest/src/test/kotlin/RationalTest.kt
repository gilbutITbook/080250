import io.kotest.core.spec.style.StringSpec
import io.kotest.property.*
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.bind
import io.kotest.property.arbitrary.int
import kotlin.math.abs

class RationalTest : StringSpec({
  "Subtraction (Arb 사용)" {
    forAll(
      // 첫번째 인자로 Arb<Rational> 인스턴스를 넘김
      object : Arb<Rational>() {
        override fun edgecase(rs: RandomSource): Rational? = null // 에지 케이스 없음
        override fun sample(rs: RandomSource): Sample<Rational> =
          Sample(Rational.of(rs.random.nextInt(), rs.random.nextInt(0, Int.MAX_VALUE)))
      }
    ){ a: Rational ->
      (a - a).num == 0
    }
  }

  val rationalArb = Arb.bind(Arb.int(),Arb.int(0,Int.MAX_VALUE)){x,y->Rational.of(x,y)}

  "Subtraction (Arb.int()와 Arb.bind() 사용)" {
    forAll(rationalArb){ a: Rational ->
      (a - a).num == 0
    }
  }

  val rationalArb2 = arbitrary { Rational.of(it.random.nextInt(), it.random.nextInt(0,Int.MAX_VALUE)) }

  "Subtraction (arbitrary() 사용)" {
    forAll(rationalArb2){ a: Rational ->
      (a - a).num == 0
    }
  }
})

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
    return "${sign*num}${if (den != 1) "/$den" else ""}"
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