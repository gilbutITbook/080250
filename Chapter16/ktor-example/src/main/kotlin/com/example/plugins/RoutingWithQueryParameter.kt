package com.example.plugins

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.h2

object RoutingWithQueryParameter {
    fun Application.configureRouting() {
        routing {
            route("/user/{name}", HttpMethod.Get) {
                param("action", "sayHello") {
                    handle {
                        call.respondHtml {
                            body { h2 { +"Hello, ${call.parameters["name"]}" } }
                        }
                    }
                }
                param("action", "sayBye") {
                    handle {
                        call.respondHtml {
                            body { h2 { +"Bye, ${call.parameters["name"]}" } }
                        }
                    }
                }
            }
        }
    }
}