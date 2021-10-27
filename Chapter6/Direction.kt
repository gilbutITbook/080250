import Direction.*

enum class Direction {
  NORTH, SOUTH, WEST, EAST
}

fun rotateClockWise(direction: Direction) = when (direction) {
  NORTH -> EAST
  EAST -> SOUTH
  SOUTH -> WEST
  WEST -> NORTH
}
