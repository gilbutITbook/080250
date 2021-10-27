import io.kotest.assertions.timing.continually
import io.kotest.assertions.timing.eventually
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.File
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@ExperimentalTime
class StringSpecWithEventually : StringSpec({
  "10초 안에 파일의 내용이 단 한줄인 경우가 있어야 함" {
    eventually(10.seconds) {  // Duration.seconds(10)을 권장
      // 주어진 기간동안 파일이 한 줄만 들어있는는 순간이 올 때까지 검사 (최대 10초)
      File("data.txt").readLines().size shouldBe 1
    }
  }
  "10초동안 파일의 내용이 계속 한줄로 유지되야 함" {
    continually(10.seconds) {
      File("data.txt").readLines().size shouldBe 1
    }
  }
})