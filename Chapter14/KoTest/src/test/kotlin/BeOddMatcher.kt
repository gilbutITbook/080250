import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult

fun beOdd() = object : Matcher<Int> {
  override fun test(value: Int): MatcherResult {
    return MatcherResult(
      value % 2 != 0,
      "$value should be odd",
      "$value should not be odd"
    )
  }
}

fun beOddLength() = beOdd().compose<Collection<*>> { it.size }