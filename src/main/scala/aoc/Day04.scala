package aoc

object Day04 {
  def toRange(dashDelimited: String): Range = {
    val split = dashDelimited.split("-").map(_.toInt)
    Range.inclusive(split(0), split(1))
  }

  def solvePartOne(input: List[String]): Int = {
    input
      .map(_.split(",").map(toRange))
      .map(ranges => ranges(0).containsSlice(ranges(1)) || ranges(1).containsSlice(ranges(0)))
      .count(_ == true)
  }

  def solvePartTwo(input: List[String]): Int = {

    def anyCommonCharacter(range1: Range, range2: Range): Boolean = range2.toList.exists(range1.contains)

    input
      .map(_.split(",").map(toRange))
      .map(ranges => anyCommonCharacter(ranges(0), ranges(1)))
      .count(_ == true)
  }
}
