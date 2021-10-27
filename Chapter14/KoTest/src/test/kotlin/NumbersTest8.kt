import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.BehaviorSpec

class NumbersTest8 : BehaviorSpec({
  Given("Arithmetic") {
    When("x is 1") {
      val x = 1
      And("increased by 1") {
        Then("result is 2") { (x + 1) shouldBe 2 }
      }
    }
  }
})