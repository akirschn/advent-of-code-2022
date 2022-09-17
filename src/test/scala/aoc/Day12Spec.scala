package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day12Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day12.solvePartOne(load("/day12-sample.txt")) shouldBe 0
    Day12.solvePartOne(load("/day12.txt")) shouldBe 0

    Day12.solvePartTwo(load("/day12-sample.txt")) shouldBe 0
    Day12.solvePartTwo(load("/day12.txt")) shouldBe 0
  }
}
