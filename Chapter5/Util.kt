package util

fun String.truncate(maxLength: Int): String {
  return if (length <= maxLength) this else substring(0, maxLength)
}
