import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.and
import io.kotest.matchers.ints.beLessThanOrEqualTo
import io.kotest.matchers.should
import io.kotest.property.checkAll
import io.kotest.property.forAll
import io.kotest.property.forNone

class NumberTestWithAssertAll : StringSpec({
  "min" {
    checkAll { a: Int, b: Int ->
      (a min b).let {
        it should (beLessThanOrEqualTo(a) and beLessThanOrEqualTo(b))
      }
    }
  }

  "min with expression" {
    forAll{ a: Int, b: Int ->
      (a min b).let {
        it <= a && it <= b
      }
    }
  }

  "min (forNone 사용)" {
    forNone { a: Int, b: Int ->
      (a min b).let {
        it > a || it > b
      }
    }
  }
})

infix fun Int.min(n: Int) = if (this < n) this else n
