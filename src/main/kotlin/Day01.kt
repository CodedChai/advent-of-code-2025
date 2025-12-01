enum class SafeKnobDirection {
  L,
  R,
}

data class SafeCrackingCode(
  val knobDirection: SafeKnobDirection,
  val amount: Int,
) {
  fun turnKnob(currentKnobValue: Int, range: IntRange): Int {
    val newValue = when (knobDirection) {
      SafeKnobDirection.L -> currentKnobValue - amount
      SafeKnobDirection.R -> currentKnobValue + amount
    }

    val rangeSize = range.last - range.first() + 1
    return ((newValue - range.first()).mod(rangeSize)) + range.first()
  }
}

fun main() {

  fun String.parseLine(): SafeCrackingCode {
    return SafeCrackingCode(
      knobDirection = SafeKnobDirection.valueOf(this.first().toString()),
      amount = this.substring(1).toInt(),
    )
  }

  fun part1(): Int {
    val inputLines = readInput("Day01")

    var currentDial = 50
    var timesAtExactlyZero = 0
    val dialRange = 0..99

    inputLines.forEach { line ->
      currentDial = line.parseLine().turnKnob(currentDial, dialRange)

      if (currentDial == 0) {
        timesAtExactlyZero++
      }
    }

    return timesAtExactlyZero
  }

  part1().println()
}