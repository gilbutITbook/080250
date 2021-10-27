package com.example.plugins

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.h2

object RoutingWithQueryParameter2GetAll {
    fun Application.configureRouting() {
        routing {
            // 예: /sum?arg=1&arg=2&arg=3 responds with 6
            get("/sum") {
                val args = call.request.queryParameters.getAll("arg")
                if (args == null) {
                    call.respondText("No data")
                    return@get
                }
                var sum = 0
                for (arg in args) {
                    val num = arg.toIntOrNull()
                    if (num == null) {
                        call.respondText("Invalid arguments")
                        return@get
                    }
                    sum += num
                }
                call.respondText("$sum")
            }
        }
    }
}