package com.example.plugins

import io.ktor.sessions.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.random.Random

data class Stat(val viewCount: Int)
private const val STAT_KEY = "STAT"

fun Application.configureSecurity() {
  // 쿠키를 이용한 세션
  install(Sessions) {
    cookie<Stat>(STAT_KEY)
  }
  /*
  // 해시 트랜스포머를 이용한 세션
  install(Sessions) {
    cookie<Stat>(STAT_KEY, SessionStorageMemory()) {
      val key = Random.nextBytes(16)
      transform(SessionTransportTransformerMessageAuthentication(key))
    }
  }
  // 암호화 트랜스포머를 이용한 세션
  install(Sessions) {
    cookie<Stat>(STAT_KEY, SessionStorageMemory()) {
      val encryptionKey = Random.nextBytes(16)
      val signKey = Random.nextBytes(16)
      transform(SessionTransportTransformerEncrypt(encryptionKey, signKey))
    }
  }
  */
  routing {
    get("/clearStat") {
      call.sessions.clear(STAT_KEY)
      call.respondRedirect("/")
    }
  }
}
