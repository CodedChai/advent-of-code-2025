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

  fun hasRepeat(id: String, partCount: Int = 2): Boolean {
    if (partCount > id.length) {
      return false
    }

    if (id.length % partCount == 0) {
      val stringSplitSize = id.length / partCount
      val stringParts = mutableListOf<String>()
      var currentIndex = 0
      while (currentIndex < id.length) {
        stringParts.add(id.substring(currentIndex, currentIndex + stringSplitSize))
        currentIndex += stringSplitSize
      }

      if (stringParts.all { it == stringParts.first() }) {
        return true
      }
    }

    return hasRepeat(id, partCount + 1)
  }

  fun part2(): Long {
    val inputLines = readInput("Day02")

    var invalidIdSum = 0L

    inputLines.forEach { inputLine ->
      val (rangeStart, rangeEnd) = inputLine.split("-")
      (rangeStart.toLong()..rangeEnd.toLong()).forEach { id ->
        val idString = id.toString()
        if (hasRepeat(idString)) {
          println(id)
          invalidIdSum += id
        }
      }
    }

    return invalidIdSum
  }

  part1().println()
  part2().println()
}