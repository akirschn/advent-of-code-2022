package aoc

import org.scalatest.matchers.should.Matchers
import org.scalatest.verbs.ShouldVerb
import org.scalatest.wordspec.AnyWordSpec

class Day05Spec extends AnyWordSpec with Matchers with ShouldVerb {

  "it" should {
    "detect vents" in {
      Day05.detectVents(FileUtils.load("/day05-sample.txt")) shouldBe 5
      Day05.detectVents(FileUtils.load("/day05.txt")) shouldBe 5280

      Day05.detectVents(FileUtils.load("/day05-sample.txt"), true) shouldBe 12
      Day05.detectVents(FileUtils.load("/day05.txt"), true) shouldBe 16716
    }
  }
}
