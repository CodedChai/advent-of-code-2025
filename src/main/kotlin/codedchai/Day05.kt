package codedchai

fun main() {

  fun part1(): Int {

    val inputLines = readInput("Day05")
    val ranges = inputLines.mapNotNull { line ->
      if ("-" !in line) {
        return@mapNotNull null
      }

      val (first, second) = line.split("-")
      first.toLong()..second.toLong()
    }

    val productIds = inputLines.mapNotNull { line ->
      if (line.any { !it.isDigit() } || line.isBlank()) {
        return@mapNotNull null
      }

      line.toLong()
    }

    ranges.println()
    productIds.println()

    val freshCount = productIds.count { productId ->
      ranges.any { range -> productId in range }
    }

    return freshCount
  }

  part1().println()
}