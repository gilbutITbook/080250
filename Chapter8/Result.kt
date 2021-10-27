// Result.kt
sealed class Result {
  class Success(val value: Any) : Result()
  open class Error(val message: String) : Result()
}
