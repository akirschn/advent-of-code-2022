package aoc

object Day03 {

  private val lowerCaseLetters = ('a' to 'z').zip(1 to 26).toMap
  private val upperCaseLetters = ('A' to 'Z').zip(27 to 52).toMap
  private val letterMapping = lowerCaseLetters ++ upperCaseLetters

  def solvePartOne(input: List[String]): Int = {
    def findCommon(first: String, second: String) = first.filter(character => second.contains(character))(0)
    val rucksacks = input.map(bothRucksacks => bothRucksacks.splitAt(bothRucksacks.length / 2))
    val commonItemsPerRucksack = rucksacks.map(rucksack => findCommon(rucksack._1, rucksack._2))
    commonItemsPerRucksack.map(c => letterMapping(c)).sum
  }

  def solvePartTwo(input: List[String]): Int = {

    def findBadge(rucksacks: List[String]): Char = {
      val letters = ('a' to 'z') ++ ('A' to 'Z')
      val isLetterPresentInAllRucksacks = letters.map(c => rucksacks.forall(single => single.contains(c)))
      val isLetterPresentInAllRucksacksWithLetter = letters.zip(isLetterPresentInAllRucksacks)
      isLetterPresentInAllRucksacksWithLetter.filter(j => j._2)(0)._1
    }

    val rucksacksGrouped = input.grouped(3).toList
    rucksacksGrouped.map(findBadge).map(c => letterMapping(c)).sum
  }
}
