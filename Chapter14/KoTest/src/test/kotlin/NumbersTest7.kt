import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe

class NumbersTest7 : FeatureSpec({
  feature("Arithmetic") {
    val x = 1
    scenario("x is 1 at first") { x shouldBe 1 }
    feature("increasing by") {
      scenario("1 gives 2") { (x + 1) shouldBe 2 }
      scenario("2 gives 3") { (x + 2) shouldBe 3 }
    }
  }
})