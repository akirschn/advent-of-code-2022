package aoc

import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day02Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day02.solvePartOne(load("/day02-sample.txt")) shouldBe 15
    Day02.solvePartOne(load("/day02.txt")) shouldBe 14827

    Day02.solvePartTwo(load("/day02-sample.txt")) shouldBe 12
    Day02.solvePartTwo(load("/day02.txt")) shouldBe 13889
  }
}
