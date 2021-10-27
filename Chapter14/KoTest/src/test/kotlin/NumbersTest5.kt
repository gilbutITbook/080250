import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.ShouldSpec

class NumbersTest5 : ShouldSpec({
    should("be equal to 0") { 0 shouldBe 0 }
    context("Addition") {
        context("1 + 2") {
            should("be equal to 3") { (1 + 2) shouldBe 3 }
            should("be equal to 2 + 1") { (1 + 2) shouldBe (2 + 1) }
        }
    }
})