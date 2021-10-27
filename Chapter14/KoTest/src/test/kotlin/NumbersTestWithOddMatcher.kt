import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.*
import io.kotest.matchers.ints.positive

class NumbersTestWithOddMatcher : StringSpec({
  "5 is odd" { 5 should beOdd() }
  "4 is not odd" { 4 shouldNot beOdd() }
  "5 is positive odd" { 5 should (beOdd() and positive()) }
  "list is odd" { listOf(1,3,5) should (beOddLength())}
})