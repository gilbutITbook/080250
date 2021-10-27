// foo, bar가 있다고 가정한다. 실제로는 없으므로 이 스크립트는 임포트문에서 오류가 난다
import app.util.foo.readInt
import app.util.bar.readInt

fun main() {
  val n = readInt() // 오류: 두 가지 readInt() 중에 하나를 선택할 수 없음
}

/*
// 두 readInt를 구분하려면 as를 써서 별명을 제공하거나, 전체 이름을 사용해야 한다
import foo.readInt as fooReadInt
import bar.readInt as barReadInt

fun main() {
  val n = fooReadInt()
  val m = barReadInt()
}
*/

/*
// 구체적 임포트(import app.util.foo.readInt)와 필요시 임포트(import app.util.bar.*)를 섞어 사용하면 
// 구체적 임포트가 우선 사용됨
import app.util.foo.readInt
import app.util.bar.*

fun main() {
  val n = readInt() // 모호하지 않음. app.util.foo.readInt을 사용
}
*/
