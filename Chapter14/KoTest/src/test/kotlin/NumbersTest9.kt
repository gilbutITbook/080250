import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.AnnotationSpec

class NumbersTest9 : AnnotationSpec() {
  @Test fun `2 + 2 should be 4`() { (2 + 2) shouldBe 4 }
  @Test fun `2 * 2 should be 4`() { (2 * 2) shouldBe 4 }
}