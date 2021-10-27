package com.example.viewCounter

import io.ktor.client.*
import io.ktor.client.request.get
import io.ktor.client.features.cookies.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val H2 = "<h2>"
private const val H2_CLOSE = "</h2>"

fun main() {
  HttpClient(){
    install(HttpCookies)
  }.use { client ->
    runBlocking {
      repeat(5) {
        val htmlText = client.get<String>("http://localhost:8080")
        val from = htmlText.indexOf(H2)
        val to = htmlText.indexOf(H2_CLOSE)
        if(from < 0 || to < 0) throw Exception("<h2>나 </h2>를 찾을 수 없습니다.")
        val message = htmlText.substring(from+H2.length, to)
        println(message)
        delay(500)
      }
    }
  }
}