package com.example.plugins

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1

object RoutingWithRedirection {
    fun Application.configureRouting() {
        routing {
            get("/") {
                call.respondRedirect("index") // 302 Found
            }
            get("index") {
                call.respondText("Main page")
            }
        }
    }
}