package com.example.passwordGen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PasswordGenApplication

fun main(args: Array<String>) {
  runApplication<PasswordGenApplication>(*args)
}
