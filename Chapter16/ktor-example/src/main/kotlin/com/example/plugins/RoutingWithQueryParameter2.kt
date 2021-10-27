package com.example.plugins

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.h2

object RoutingWithQueryParameter2 {
    fun Application.configureRouting() {
        routing {
            // 예: /sum?left=2&right=3 responds with 5
            get("/sum") {
                val left = call.request.queryParameters["left"]?.toIntOrNull()
                val right = call.request.queryParameters["right"]?.toIntOrNull()
                if (left != null && right != null) {
                    call.respondText("${left + right}")
                } else {
                    call.respondText("Invalid arguments")
                }
            }
        }
    }
}