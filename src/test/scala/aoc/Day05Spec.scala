package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day05Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {

    val sampleStack = List(
      List('Z', 'N'),
      List('M', 'C', 'D'),
      List('P')
    )

    val stack = List(
      List('W', 'B', 'D', 'N', 'C', 'F', 'J'),
      List('P', 'Z', 'V', 'Q', 'L', 'S', 'T'),
      List('P', 'Z', 'B', 'G', 'J', 'T'),
      List('D', 'T', 'L', 'J', 'Z', 'B', 'H', 'C'),
      List('G', 'V', 'B', 'J', 'S'),
      List('P', 'S', 'Q'),
      List('B', 'V', 'D', 'F', 'L', 'M', 'P', 'N'),
      List('P', 'S', 'M', 'F', 'B', 'D', 'L', 'R'),
      List('V', 'D', 'T', 'R')
    )

    Day05.solvePartOne(load("/day05-sample.txt"), sampleStack) shouldBe "CMZ"
    Day05.solvePartOne(load("/day05.txt"), stack) shouldBe "LBLVVTVLP"

    Day05.solvePartTwo(load("/day05-sample.txt"), sampleStack) shouldBe "MCD"
    Day05.solvePartTwo(load("/day05.txt"), stack) shouldBe "TPFFBDRJD"
  }
}
