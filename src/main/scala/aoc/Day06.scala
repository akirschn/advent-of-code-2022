package aoc

object Day06 {
  def numDistinctCharsInString(input: String) = input.toList.distinct.length

  def numProcessedCharsAfterFirstPatternAppearance(input: String, patternLength: Int): Int = {
    val slices: List[String] = input.sliding(patternLength).toList
    val slicesWithNumDistinctCharacters = slices.map(slice => (slice, numDistinctCharsInString(slice))).filter(_._2 == patternLength).head._1
    input.indexOf(slicesWithNumDistinctCharacters) + patternLength
  }

  def solvePartOne(input: List[String]): Int = numProcessedCharsAfterFirstPatternAppearance(input.head, 4)

  def solvePartTwo(input: List[String]): Int = numProcessedCharsAfterFirstPatternAppearance(input.head, 14)
}
