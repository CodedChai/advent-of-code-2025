package codedchai

data class MathProblem(
  val numbers: List<Long>,
  val arithmeticOperation: String,
) {
  fun doTheMath(): Long {
    return if (arithmeticOperation == "+") {
      numbers.sum()
    } else {
      numbers.reduce(Long::times)
    }
  }
}

fun main() {

  fun part1(): Long {
    val input = readInput("Day06").map { line -> line.trim().split(" ").mapNotNull { it.ifBlank { null } } }

    val mathProblems = mutableListOf<MathProblem>()

    for (columnIndex in input.first().indices) {
      val numbers = mutableListOf<Long>()
      for (rowIndex in input.indices) {
        val inputString = input[rowIndex][columnIndex]
        if (inputString.all { it.isDigit() }) {
          numbers.add(inputString.toLong())
        } else {
          mathProblems.add(
            MathProblem(
              numbers,
              inputString
            )
          )
        }

      }
    }

    return mathProblems.sumOf { it.doTheMath() }
  }

  part1().println()
}