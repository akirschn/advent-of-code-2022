package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day08Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day08.solvePartOne(load("/day08-sample.txt")) shouldBe 21
    Day08.solvePartOne(load("/day08.txt")) shouldBe 1647

    Day08.solvePartTwo(load("/day08-sample.txt")) shouldBe 8
    Day08.solvePartTwo(load("/day08.txt")) shouldBe 392080
  }
}
