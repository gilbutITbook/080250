import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.DescribeSpec

class NumbersTest4 : DescribeSpec({
    describe("Addition") {
        context("1 + 2") {
            it("should give 3") { (1 + 2) shouldBe 3 }
        }
    }
})