package com.example.randomGen

data class GeneratorResult<T>(
  val status: String?,
  val values: List<T>
)

fun <T>errorResult(status: String) =
  GeneratorResult<T>(status, emptyList())
fun <T>successResult(values: List<T>) =
  GeneratorResult<T>(null, values)