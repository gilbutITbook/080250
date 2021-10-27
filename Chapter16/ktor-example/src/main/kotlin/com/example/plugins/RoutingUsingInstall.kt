package com.example.plugins

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

object RoutingUsingInstall {
    fun Application.configureRouting() {
        install(Routing) {
            get("/") {
                call.respondText("Hello World!")
            }
        }
    }
}