package codedchai

import kotlin.math.max
import kotlin.math.min

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

  fun part2(): Long {
    val inputLines = readInput("Day05")
    val ranges = inputLines.mapNotNull { line ->
      if ("-" !in line) {
        return@mapNotNull null
      }

      val (first, second) = line.split("-")
      first.toLong()..second.toLong()
    }.toMutableList()

    var anyChanges = true
    while (anyChanges) {
      anyChanges = false
      // easiest to do one by one to avoid oopsies
      for (index in ranges.indices) {
        val currentRange = ranges[index]
        for (innerIndex in ranges.indices) {
          if (innerIndex == index) {
            continue
          }
          val innerRange = ranges[innerIndex]
          if (currentRange.first in innerRange || currentRange.last in innerRange) {
            anyChanges = true
            ranges.remove(currentRange)
            ranges.remove(innerRange)
            val newRange = min(currentRange.first, innerRange.first)..max(currentRange.last, innerRange.last)
            ranges.add(newRange)
            break
          }
        }
        if (anyChanges) {
          break
        }
      }
    }

    var totalFreshProducts = 0L

    ranges.forEach { range ->
      totalFreshProducts += 1 + (range.last - range.first)
    }

    return totalFreshProducts
  }

  part1().println()
  part2().println()
}