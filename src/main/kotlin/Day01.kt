enum class SafeKnobDirection {
  L,
  R,
}

data class SafeCrackingCode(
  val knobDirection: SafeKnobDirection,
  val amount: Int,
) {
  fun turnKnobPart1(currentKnobValue: Int, range: IntRange): Int {
    val newValue = when (knobDirection) {
      SafeKnobDirection.L -> currentKnobValue - amount
      SafeKnobDirection.R -> currentKnobValue + amount
    }

    val rangeSize = range.last - range.first() + 1
    return ((newValue - range.first()).mod(rangeSize)) + range.first()
  }

  fun turnKnobPart2(currentKnobValue: Int, range: IntRange): Int {
    return when (knobDirection) {
      SafeKnobDirection.L -> currentKnobValue - amount
      SafeKnobDirection.R -> currentKnobValue + amount
    }
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
      currentDial = line.parseLine().turnKnobPart1(currentDial, dialRange)

      if (currentDial == 0) {
        timesAtExactlyZero++
      }
    }

    return timesAtExactlyZero
  }


  fun part2(): Int {
    val inputLines = readInput("Day01")

    var currentDial = 50
    var timesPassedZero = 0
    val range = 0..99

    inputLines.forEach { line ->
      val starting = currentDial
      val instructions = line.parseLine()
      val dialBeforeWrap = instructions.turnKnobPart2(currentDial, range)

      /**
       * When it should increase:
       * - Move right and goes to 100+ (divide by 100 to decide how many times)
       * - Moves left and lands on 0
       * - Moves left and crosses 0, then for each 100 in the negative
       * - If it starts on zero and moves less than 100 there is no crossing
       */

      val rangeSize = range.last - range.first() + 1
      currentDial = ((dialBeforeWrap - range.first()).mod(rangeSize)) + range.first()

      when (instructions.knobDirection) {
        SafeKnobDirection.R -> {
          timesPassedZero += dialBeforeWrap / rangeSize
        }

        SafeKnobDirection.L -> {
          if (starting == 0) {
            timesPassedZero += instructions.amount / rangeSize
          } else if (instructions.amount >= starting) {
            timesPassedZero += 1 + (instructions.amount - starting) / rangeSize
          }
        }
      }

      println("$line - $starting - $dialBeforeWrap - $currentDial - $timesPassedZero")
    }

    return timesPassedZero
  }

  part1().println()
  part2().println()
}