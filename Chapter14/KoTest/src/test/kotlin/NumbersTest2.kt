import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.WordSpec

class NumbersTest2 : WordSpec({
    "1 + 2" should {
        "be equal to 3" { (1 + 2) shouldBe 3 }
        "be equal to 2 + 1" { (1 + 2) shouldBe (2 + 1) }
    }
    "Addition" `when` {
        "1 + 2" should {
            "be equal to 3" { (1 + 2) shouldBe 3 }
            "be equal to 2 + 1" { (1 + 2) shouldBe (2 + 1) }
        }
    }
    "Addition2" When {
        "1 + 2" should {
            "be equal to 3" { (1 + 2) shouldBe 3 }
            "be equal to 2 + 1" { (1 + 2) shouldBe (2 + 1) }
        }
    }
})