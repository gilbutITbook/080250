package com.example.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.sessions.*
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.h2

fun Application.configureRouting() {
  routing {
    get("/") {
      call.rootPage()
    }
  }
}

private suspend fun ApplicationCall.rootPage() {
  val stat = sessions.getOrSet { Stat(0) }
  sessions.set(stat.copy(viewCount = stat.viewCount + 1))
  respondHtml {
    body {
      h2 { +"이 페이지에 ${stat.viewCount} 번 방문하셨습니다." }
      a("/clearStat") { +"방문 횟수 재설정하기" }
    }
  }
}
