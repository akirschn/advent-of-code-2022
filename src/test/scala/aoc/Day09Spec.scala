package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day09Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day09.solvePartOne(load("/day09-sample.txt")) shouldBe 13
    Day09.solvePartOne(load("/day09.txt")) shouldBe 5779

    Day09.solvePartTwo(load("/day09-sample.txt")) shouldBe 1
    Day09.solvePartTwo(load("/day09.txt")) shouldBe 2331
  }
}
