package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day04Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day04.solvePartOne(load("/day04-sample.txt")) shouldBe 2
    Day04.solvePartOne(load("/day04.txt")) shouldBe 518

    Day04.solvePartTwo(load("/day04-sample.txt")) shouldBe 4
    Day04.solvePartTwo(load("/day04.txt")) shouldBe 909
  }
}
