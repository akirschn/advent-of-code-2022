package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day11Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day11.solvePartOne(load("/day11-sample.txt")) shouldBe 10605
    Day11.solvePartOne(load("/day11.txt")) shouldBe 50830

    Day11.solvePartTwo(load("/day11-sample.txt")) shouldBe 2713310158L
    Day11.solvePartTwo(load("/day11.txt")) shouldBe 14399640002L
  }
}
