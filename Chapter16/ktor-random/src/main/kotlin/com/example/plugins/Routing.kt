package com.example.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.request.*
import kotlinx.html.*
import java.util.*
import kotlin.random.Random

fun Application.configureRouting() {
  routing {
    get("/") {
      call.rootPage()
    }
  }
}

const val FROM_KEY = "from"
const val TO_KEY = "to"
const val COUNT_KEY = "count"

data class FormData(val from: Int?=null, val to: Int?=null, val count: Int?=null, val generate: Boolean=false)

private inline fun <T> T?.runIfNull(block:()->Unit): T? {
  if(this==null) block()
  return this
}

fun checkFormData(param: Parameters):Pair<FormData,Map<String,String>?> {
  if(param.entries().isEmpty()) return FormData() to null

  val errorMap = mutableMapOf<String,String>()

  // fieldName에 해당하는 파라미터 값이 널이거나 제대로 정수로 파싱되는지 검사한다
  // 제대로 파싱되는 경우 파싱해 정수로 변환한 값을 돌려주고, 정상이 아닌 경우는 null을 반환한다
  fun checkIntError(fieldName:String): Int? =
    param[fieldName].runIfNull {
      errorMap[fieldName] = "필드에 값을 입력해주세요"
    }?.let { vv ->
      vv.toIntOrNull().runIfNull {
        errorMap[fieldName] = "올바른 정수 값을 입력해주세요"
      }
    }

  // 파라미터로 문자열이 들어왔는지 체크하고, 문자열을 정수로 파싱하면서 문제가 있는지 파악한다
  val from = checkIntError(FROM_KEY)
  val to = checkIntError(TO_KEY)
  val count = checkIntError(COUNT_KEY)

  // errorMap에 오류가 없으면 모든 데이터가 일단 정수로 제대로 검증된 상태다.
  // 이제 실제 각 값들이 우리가 원하는 조건을 만족하는지 검증한다
  if(errorMap.isEmpty()) {
    if(from!! >= to!!)  errorMap[FROM_KEY] = "시작 값은 끝 값보다 더 작아야 합니다."
    if(count!! <= 0) errorMap[COUNT_KEY] = "생성할 난수 개수는 0보다 커야 합니다."
  }

  return FormData(from,to,count,errorMap.isEmpty()) to (if(errorMap.isEmpty()) null else errorMap)
}

private suspend fun ApplicationCall.rootPage() {
  val (data, errors) = checkFormData(request.queryParameters)
  val (from, to, count, generate) = data

  fun P.appendError(key:String) {
    if(errors!=null && errors.containsKey(key)) {
      strong {
        + errors[key]!!
      }
    }
  }

  respondHtml {
    body {
      h1 { +"난수 생성" }
      form(action = "/", method = FormMethod.get) {
        p { +"시작(생성되는 난수에 포함): " }
        p {
          numberInput(name = FROM_KEY) {
            value = from?.toString() ?: "1"
          }
          appendError(FROM_KEY)
        }
        p { +"끝(생성되는 난수에 미포함): " }
        p {
          numberInput(name = TO_KEY) {
            value = to?.toString() ?: "100"
          }
          appendError(TO_KEY)
        }
        p { +"생성할 난수 개수: " }
        p {
          numberInput(name = COUNT_KEY) {
            value = count?.toString() ?: "10"
          }
          appendError(COUNT_KEY)
        }
        p { submitInput { value = "Generate" } }
      }
      if(generate) {
        h1 { +"결과" }
        p {
          + (List(count!!) { Random.nextInt(from!!,to!!)}).joinToString()
        }
      }
    }
  }
}
