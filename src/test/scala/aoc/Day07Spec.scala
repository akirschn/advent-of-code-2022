package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day07Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day07.solvePartOne(load("/day07-sample.txt")) shouldBe 95437
    Day07.solvePartOne(load("/day07.txt")) shouldBe 2104783

    Day07.solvePartTwo(load("/day07-sample.txt")) shouldBe 24933642
    Day07.solvePartTwo(load("/day07.txt")) shouldBe 5883165
  }
}
