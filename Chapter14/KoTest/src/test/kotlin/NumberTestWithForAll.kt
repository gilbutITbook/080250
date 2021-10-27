import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan

class NumberTestWithForAll : StringSpec({
  val numbers = Array(10) { it + 1 }
  "invalid numbers" {
    assertSoftly {
      numbers.forAll { it shouldBeLessThan 5 }
      numbers.forAll { it shouldBeGreaterThan 3 }
    }
  }
})