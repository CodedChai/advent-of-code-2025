fun main() {
  fun part1(): Long {
    val inputLines = readInput("Day02")

    var invalidIdSum = 0L

    inputLines.forEach { inputLine ->
      val (rangeStart, rangeEnd) = inputLine.split("-")
      (rangeStart.toLong()..rangeEnd.toLong()).forEach { id ->
        val idString = id.toString()
        if (idString.length % 2 == 0 &&
          idString.substring(0, idString.length / 2) == idString.substring(idString.length / 2)
        ) {
          println(id)
          invalidIdSum += id
        }
      }
    }

    return invalidIdSum
  }

  part1().println()
}