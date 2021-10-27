package com.example.predefinedCookie

import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.client.features.cookies.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val H2 = "<h2>"
private const val H2_CLOSE = "</h2>"

fun main() {
  HttpClient(){
    install(Logging) {
      logger = Logger.DEFAULT
      level = LogLevel.ALL
    }
    install(HttpCookies) {
      storage = ConstantCookiesStorage(Cookie("STAT", "viewCount=%23i2"))
    }
  }.use { client ->
    println("START of runBlocking")
    runBlocking {
      println("START of repeat")
      repeat(5) {
        println("START of get")
        val htmlText = client.get<String>("http://localhost:8080")
        val from = htmlText.indexOf(H2)
        val to = htmlText.indexOf(H2_CLOSE)
        if(from < 0 || to < 0) throw Exception("<h2>나 </h2>를 찾을 수 없습니다.")
        val message = htmlText.substring(from+H2.length, to)
        println(message)
        delay(500)
        println("END of get")
      }
      println("END of repeat")
    }
    println("END of runBlocking")
  }
  println("END of Application")
}