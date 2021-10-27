import io.kotest.matchers.string.shouldEndWith
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class ParseTest: StringSpec({
  "invalid string" {
    val e = shouldThrow<NumberFormatException>{ "abc".toInt() }
    e.message shouldEndWith "\"abc\""
  }
})