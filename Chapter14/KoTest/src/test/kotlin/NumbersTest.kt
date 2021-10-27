import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.StringSpec

class NumbersTest : StringSpec({
    "2 + 2 should be 4" { (2 + 2) shouldBe 4 }
    "2 * 2 should be 4" { (2 * 2) shouldBe 4 }
})