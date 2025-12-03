fun main() {

  fun part1(): Long {
    val inputLines = readInput("Day03")

    val bestBatteries = inputLines.map { batteries ->

      val bestFirstDigit = batteries.dropLast(1).maxBy { it.digitToInt() }.digitToInt()
      val indexOfFirst = batteries.indexOfFirst { it.digitToInt() == bestFirstDigit }
      val bestSecondDigit = batteries.drop(indexOfFirst + 1).maxBy { it.digitToInt() }.digitToInt()

      println("Selected $bestFirstDigit$bestSecondDigit")
      (bestFirstDigit * 10) + bestSecondDigit
    }

    return bestBatteries.sum().toLong()
  }

  part1().println()
}