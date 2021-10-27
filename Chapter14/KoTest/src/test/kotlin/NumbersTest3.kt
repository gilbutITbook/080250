import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.FunSpec

class NumbersTest3  : FunSpec({
    test("0 should be equal to 0") { 0 shouldBe 0 }
    context("Arithmetic") {
        context("Addition") {
            test("2 + 2 should be 4") { (2 + 2) shouldBe 4 }
        }
        context("Multiplication") {
            test("2 * 2 should be 4") { (2 * 2) shouldBe 4 }
        }
    }
})