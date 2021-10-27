package com.example

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.runBlocking
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

fun main() {
  runBlocking {
    HttpClient().use {
      val url = "http://worldtimeapi.org/api/timezone/Australia/Brisbane.txt"
      val result = it.get<String>(url)
      val prefix = "day_of_week:"
      val from = result.indexOf(prefix)
      if (from < 0) return@runBlocking
      val to = result.indexOf('\n', from + 1)
      if (to < 0) return@runBlocking
      val dow = result
        .substring(from + prefix.length, to)
        .trim()
        .toInt()
      println("브리즈번은 ${DayOfWeek.of(dow).getDisplayName(TextStyle.FULL, Locale.KOREAN)} 입니다!")
    }
  }
}

const val URL = "http://www.naver.com"

suspend fun headerExample() {
  HttpClient().use {
    it.get<ByteArray>(URL) {
      header("Cache-Control","no-cache")
    }
  }
}

suspend fun headersExample() {
  HttpClient().use {
    it.get<ByteArray>(URL) {
      headers {
        clear()
        append("Cache-Control", "no-cache")
        append("My-Header", "My-Value")
      }
    }
  }
}

suspend fun agentExample() {
  HttpClient(Java){
    install(UserAgent) {
      agent = "Test Browser"
    }
  }.use {
    it.get<ByteArray>(URL) {
      headers {
        clear()
        append("Cache-Control", "no-cache")
        append("My-Header", "My-Value")
      }
    }
  }
}

suspend fun outgoingExample() {
  HttpClient(){
    TextContent("""{"my_key1":1, "my_key2":"string"}""", ContentType.Application.Json)
  }.use {
    it.post<String>(URL)
  }
}

suspend fun outgoingJsonExample() {
  HttpClient(){
    data class ExampleData(val my_key: Int, val my_key2: String)
    install(JsonFeature)
  }.use {
    it.post<String>(URL)
  }
}

suspend fun formSubmitExample() {
  HttpClient().submitForm<String>(
    url = "http://localhost:8080",
    encodeInQuery = true,
    formParameters = parametersOf(
      "from" to listOf("0"),
      "to" to listOf("0"),
      "count" to listOf("0"),
      "generate" to emptyList()
    )
  )
}