package aoc

object Day02 {

  def solvePartOne(input: List[String]): Int = {

    val betterReadableInput = input.map(
      _.replace("A", "R")
        .replace("B", "P")
        .replace("C", "S")
        .replace("X", "R")
        .replace("Y", "P")
        .replace("Z", "S")
    )

    val plays = betterReadableInput.map(playStr => playStr.split(" ").toList)

    val scores = plays.map {

      case List("R", "R") => 1 + 3
      case List("P", "R") => 1 + 0
      case List("S", "R") => 1 + 6

      case List("R", "P") => 2 + 6
      case List("P", "P") => 2 + 3
      case List("S", "P") => 2 + 0

      case List("R", "S") => 3 + 0
      case List("P", "S") => 3 + 6
      case List("S", "S") => 3 + 3

      case _ => throw new NotImplementedError
    }

    scores.sum
  }

  def solvePartTwo(input: List[String]): Int = {

    val betterReadableInput = input.map(playStr => playStr.split(" ").toList).map {
      case List("A", "X") => List("R", "L")
      case List("A", "Y") => List("R", "D")
      case List("A", "Z") => List("R", "W")

      case List("B", "X") => List("P", "L")
      case List("B", "Y") => List("P", "D")
      case List("B", "Z") => List("P", "W")

      case List("C", "X") => List("S", "L")
      case List("C", "Y") => List("S", "D")
      case List("C", "Z") => List("S", "W")

      case _ => throw new NotImplementedError
    }

    val scores = betterReadableInput.map {
      case List("R", "L") => 3 + 0
      case List("R", "D") => 1 + 3
      case List("R", "W") => 2 + 6

      case List("P", "L") => 1 + 0
      case List("P", "D") => 2 + 3
      case List("P", "W") => 3 + 6

      case List("S", "L") => 2 + 0
      case List("S", "D") => 3 + 3
      case List("S", "W") => 1 + 6

      case _ => throw new NotImplementedError
    }

    scores.sum
  }
}
