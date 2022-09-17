package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day03Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day03.solvePartOne(load("/day03-sample.txt")) shouldBe 157
    Day03.solvePartOne(load("/day03.txt")) shouldBe 7872

    Day03.solvePartTwo(load("/day03-sample.txt")) shouldBe 70
    Day03.solvePartTwo(load("/day03.txt")) shouldBe 2497
  }
}
