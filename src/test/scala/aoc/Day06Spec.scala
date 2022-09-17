package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day06Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day06.solvePartOne(load("/day06-sample.txt")) shouldBe 7
    Day06.solvePartOne(load("/day06.txt")) shouldBe 1300

    Day06.solvePartTwo(load("/day06-sample.txt")) shouldBe 19
    Day06.solvePartTwo(load("/day06.txt")) shouldBe 3986
  }
}
