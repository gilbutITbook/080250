import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.FreeSpec

class NumbersTest6  : FreeSpec({
  "0 should be equal to 0" { 0 shouldBe 0 }
  "Addition" - {
    "1 + 2" - {
      "1 + 2 should be equal to 3" { (1 + 2) shouldBe 3 }
      "1 + 2 should be equal to 2 + 1" { (1 + 2) shouldBe (2 + 1) }
    }
  }
})