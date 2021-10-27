package fixutre

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.listeners.*
import io.kotest.core.listeners.ProjectListener
import io.kotest.core.spec.AutoScan
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe
import kotlin.reflect.KClass

object SpecLevelListener : TestListener {
  override val name:String = "SpecLevelListener"

  override suspend fun prepareSpec(kclass: KClass<out Spec>) {
    println("PrepareSpec(in SpecLevelListener): ${kclass.qualifiedName}")
  }

  override suspend fun beforeSpec(spec: Spec) {
    println("BeforeSpec: ${spec.materializeRootTests().joinToString { it.testCase.displayName }}")
  }

  override suspend fun beforeTest(testCase: TestCase) {
    println("BeforeTest: ${testCase.displayName}")
  }

  override suspend fun afterTest(testCase: TestCase, result: TestResult) {
    println("AfterTest: ${testCase.displayName}")
  }

  override suspend fun afterSpec(spec: Spec) {
    println("AfterSpec: ${spec.materializeRootTests().joinToString { it.testCase.displayName }}")
  }

  override suspend fun finalizeSpec(kclass: KClass<out Spec>, results: Map<TestCase, TestResult>) {
    println("FinalizeSpec(in SpecLevelListener): ${kclass.qualifiedName}")
  }
}

class NumbersTestWithFixture1 : FunSpec() {
  init {
    context("Addition") {
      test("2 + 2") {
        2 + 2 shouldBe 4
      }
      test("4 * 4") {
        4 + 4 shouldBe 8
      }
    }
  }

  override fun listeners() = listOf(SpecLevelListener)
}

class NumbersTestWithFixture2 : FunSpec() {
  init {
    context("Multiplication") {
      test("2 * 2") {
        2 * 2 shouldBe 4
      }
      test("4 * 4") {
        4 * 4 shouldBe 16
      }
    }
  }

  override fun listeners() = listOf(SpecLevelListener)
}

object MyProjectListener : ProjectListener, TestListener {
  override val name: String = "MyProjectListener"

  override suspend fun beforeProject() { println("Before project") }

  override suspend fun afterProject() { println("After project") }

  override suspend fun prepareSpec(kclass: KClass<out Spec>) {
    println("PrepareSpec: ${kclass.qualifiedName}")
  }

  override suspend fun finalizeSpec(kclass: KClass<out Spec>, results: Map<TestCase, TestResult>) {
    println("FinalizeSpec: ${kclass.qualifiedName}")
  }
}

object ProjectConfig: AbstractProjectConfig() {
  override fun listeners() = listOf(MyProjectListener)
}