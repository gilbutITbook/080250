package fixutre

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.FileReader

class FileTest : FunSpec() {
  val reader = autoClose(FileReader("data.txt"))

  init {
    test("Line count") {
      reader.readLines().isNotEmpty() shouldBe true
    }
  }
}