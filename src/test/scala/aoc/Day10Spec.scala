package aoc

import aoc.Day10.{AddX, Noop}
import aoc.FileUtils.load
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Day10Spec extends AnyFlatSpec with Matchers {

  "The code for the day" should "solve the puzzle correctly" in {
    Day10.solvePartOne(load("/day10-sample.txt")) shouldBe 13140
    Day10.solvePartOne(load("/day10.txt")) shouldBe 12840

    Day10.solvePartTwo(load("/day10-sample.txt")) shouldBe 0
    Day10.solvePartTwo(load("/day10.txt")) shouldBe 0
  }
}
