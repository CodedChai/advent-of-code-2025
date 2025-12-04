package codedchai

import kotlin.math.pow

fun main() {


  fun findLargestDigits(digitsLeft: Int = 12, currentIndex: Int = -1, batteries: String): List<Int> {
    if (digitsLeft == 0) {
      return emptyList()
    }
    val bestFirstDigit =
      batteries.drop(currentIndex + 1).dropLast(digitsLeft - 1).maxBy { it.digitToInt() }.digitToInt()
    val indexOfFirst =
      batteries.drop(currentIndex + 1).indexOfFirst { it.digitToInt() == bestFirstDigit } + currentIndex + 1

    return listOf(bestFirstDigit) + findLargestDigits(digitsLeft - 1, indexOfFirst, batteries)
  }

  fun part1(): Long {
    val inputLines = readInput("Day03")

    val bestBatteries = inputLines.map { batteries ->
      val bestCombo = findLargestDigits(2, -1, batteries)
      println("Selected ${bestCombo.first()}${bestCombo.last()}")
      (bestCombo.first() * 10) + bestCombo.last()
    }

    return bestBatteries.sum().toLong()
  }

  fun part2(): Long {
    val inputLines = readInput("Day03")

    val bestBatteries = inputLines.map { batteries ->
      val bestCombo = findLargestDigits(12, -1, batteries)
      println("Selected ${bestCombo.first()}${bestCombo.last()}")
      bestCombo.mapIndexed { index, batteryPower ->
        batteryPower * (10.0.pow((bestCombo.size - (index + 1)).toDouble()))
      }.sum().toLong()
    }

    return bestBatteries.sum()
  }

  part1().println()
  part2().println()
}