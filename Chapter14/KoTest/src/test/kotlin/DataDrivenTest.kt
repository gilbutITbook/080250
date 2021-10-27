import io.kotest.core.spec.style.StringSpec
import io.kotest.data.*
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual

class DataDrivenTest : StringSpec({
  "Minimum" {
    forAll(
      row(1, 1),
      row(1, 2),
      row(2, 1)
    ) { a: Int, b: Int ->
      (a min b).let { it <= a && it <= b }
    }
  }
})

class DataDrivenTest2 : StringSpec({
  "Minimum" {
    forAll(
      table(
        headers("name", "age"),
        row("John", 20),
        row("Harry", 25),
        row("Bob", 16)
      )
    ) { name, age ->
      age shouldBeGreaterThanOrEqual 18
    }
  }
})