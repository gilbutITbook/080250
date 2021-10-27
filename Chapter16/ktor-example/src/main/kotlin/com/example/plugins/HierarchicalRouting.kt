package com.example.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

object HierarchicalRouting {
    fun Application.configureRouting() {
        routing {
            method(HttpMethod.Get) {
                route("user/{name}") {
                    route("sayHello") {
                        handle {
                            call.respondText("Hello, ${call.parameters["name"]}")
                        }
                    }
                    route("sayBye") {
                        handle {
                            call.respondText("Bye, ${call.parameters["name"]}")
                        }
                    }
                }
            }
        }
    }
}