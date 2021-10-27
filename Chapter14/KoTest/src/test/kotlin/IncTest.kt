import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCaseConfig
import io.kotest.matchers.shouldBe

class IncTest : FunSpec() {
  var x = 0
  init {
    context("Increment") {
      println("Increment : ${this@IncTest} ${this}")
      test("prefix") {
        println("prefix: ${this@IncTest} ${this@context} ${this}")
        ++x shouldBe 1
      }
      test("postfix") {
        println("postfix: ${this@IncTest} ${this@context} ${this}")
        x++ shouldBe 0
      }
    }
  }
}

object IncTestProjectConfig : AbstractProjectConfig() {
  override val isolationMode = IsolationMode.InstancePerTest
}