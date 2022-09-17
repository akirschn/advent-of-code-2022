package aoc

import scala.annotation.tailrec

object Day01 {

  @tailrec
  def sumCalories(calories: List[String], currentSum: Int, collectedSums: List[Int]): List[Int] =
    calories match {
      case "" :: tail   => sumCalories(tail, 0, collectedSums :+ currentSum)
      case head :: tail => sumCalories(tail, currentSum + head.toInt, collectedSums)
      case Nil          => collectedSums
    }

  def solvePartOne(input: List[String]): Int =
    sumCalories(input :+ "", 0, List.empty).sorted.reverse.head

  def solvePartTwo(input: List[String]): Int =
    sumCalories(input :+ "", 0, List.empty).sorted.reverse.take(3).sum
}
