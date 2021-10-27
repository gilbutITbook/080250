package com.example.plugins

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1

object RoutingWithOptionalParameter {
    fun Application.configureRouting() {
        routing {
            get("hello/{userName?}") {
                call.respondHtml {
                    body {
                        h1 {+"Hello, ${call.parameters["userName"] ?: "모르는분"}"}
                    }
                }
            }
        }
    }
}