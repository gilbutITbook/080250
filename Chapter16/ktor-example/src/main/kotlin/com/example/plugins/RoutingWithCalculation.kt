package com.example.plugins

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1

object RoutingWithCalculation {
    fun Application.configureRouting() {
        routing {
            get("/calc/{data...}") {
                val data = call.parameters.getAll("data") ?: emptyList()
                call.respondHtml {
                    body {
                        h1 {
                            if (data.size != 3) {
                                +"Invalid data"
                                return@h1
                            }
                            val (op, argStr1, argStr2) = data
                            val arg1 = argStr1.toBigIntegerOrNull()
                            val arg2 = argStr2.toBigIntegerOrNull()
                            if (arg1 == null || arg2 == null) {
                                +"Integer numbers expected"
                                return@h1
                            }
                            val result = when (op) {
                                "+" -> arg1 + arg2
                                "-" -> arg1 - arg2
                                "*" -> arg1 * arg2
                                "/" -> arg1 / arg2
                                else -> null
                            }
                            +(result?.toString() ?: "Invalid operation")
                        }
                    }
                }
            }
        }
    }
}