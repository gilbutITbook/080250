package com.example.randomGen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RandomGenerator

fun main(args: Array<String>) {
  runApplication<RandomGenerator>(*args)
}
