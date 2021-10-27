package com.example.randomGen

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@RequestMapping("/random")
class RandomGeneratorController {
  @RequestMapping("/int/from/{from}/to/{to}/quantity/{quantity}")
  fun genIntegers(
    @PathVariable("from") fromStr: String,
    @PathVariable("to") toStr: String,
    @PathVariable("quantity") quantityStr: String
  ): GeneratorResult<Int> {
    val from = fromStr.toIntOrNull()
      ?: return errorResult("Range start must be an integer")
    val to = toStr.toIntOrNull()
      ?: return errorResult("Range end must be an integer")
    val quantity = quantityStr.toIntOrNull()
      ?: return errorResult("Quantity must be an integer")

    if (quantity <= 0) return errorResult("Quantity must be positive")
    if (from > to) return errorResult("Range may not be empty")

    val values = (1..quantity).map { Random.nextInt(from, to + 1) }
    return successResult(values)
  }
}