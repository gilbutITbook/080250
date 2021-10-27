import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.*
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe

class NumbersTestWithInspectors : StringSpec({
  val numbers = Array(10) { it + 1 }

  "all are non-negative" {
    numbers.forAll { it shouldBeGreaterThanOrEqual 0 }
  }

  "none is zero" { numbers.forNone { it shouldBe 0 } }

  "a single 10" { numbers.forOne { it shouldBe 10 } }

  "at most one 0" { numbers.forAtMostOne { it shouldBe 0 } }

  "at least one odd number" {
    numbers.forAtLeastOne { it % 2 shouldBe 1 }
  }

  "at most five odd numbers" {
    numbers.forAtMost(5) { it % 2 shouldBe 1 }
  }

  "at least three even numbers" {
    numbers.forAtLeast(3) { it % 2 shouldBe 0 }
  }

  "some numbers are odd" { numbers.forAny { it % 2 shouldBe 1 } }

  "some but not all numbers are even" {
    numbers.forSome { it % 2 shouldBe 0 }
  }

  "exactly five numbers are even" {
    numbers.forExactly(5) { it % 2 shouldBe 0 }
  }
})