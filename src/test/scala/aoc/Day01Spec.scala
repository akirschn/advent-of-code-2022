package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day01Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day01.solvePartOne(load("/day01-sample.txt")) shouldBe 24000
    Day01.solvePartOne(load("/day01.txt")) shouldBe 70698

    Day01.solvePartTwo(load("/day01-sample.txt")) shouldBe 45000
    Day01.solvePartTwo(load("/day01.txt")) shouldBe 206643
  }
}
