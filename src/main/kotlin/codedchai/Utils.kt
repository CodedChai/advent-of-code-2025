package codedchai

import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String): List<String> {
  val paths = listOf(
    "../../../resources/$name.txt",
    "src/main/resources/$name.txt",
    "resources/$name.txt",
    "$name.txt",
    "../../../../resources/$name.txt",
  )

  for (pathStr in paths) {
    try {
      return Path(pathStr).readText().trim().lines()
    } catch (_: Exception) {
      continue
    }
  }

  throw IllegalArgumentException("Could not find input file: $name.txt")
}


fun readGridInput(name: String): Grid<Char> {
  val input = readInput(name).flatMapIndexed { yIndex, line ->
    line.mapIndexed { xIndex, char ->
      Vec2(xIndex.toLong(), yIndex.toLong()) to char
    }
  }.toMap(hashMapOf())

  return Grid(input)
}

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)
