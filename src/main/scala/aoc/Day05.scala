package aoc

import scala.annotation.tailrec

object Day05 {

  def detectVents(input: List[String], includeDiagonals: Boolean = false): Int = {
    val coordinates = loadCoordinates(input)
    val filledCoordinates =
      if (includeDiagonals) fillCoordinatesWithDiagonals(coordinates) else fillCoordinates(coordinates)
    val cs = (for (i <- 0 to 1000; j <- 0 to 1000) yield (i,j) -> 0).toMap
    val csUpdated = updateCoordinateSystem(cs, filledCoordinates)
    csUpdated.count(_._2 >= 2)
  }

  def loadCoordinates(input: List[String]): List[List[Int]] =
    input.map(_.split(" -> ").toList.flatMap(_.split(",")).map(_.toInt))

  def fillCoordinates(ventCoordinates: List[List[Int]]): List[(Int, Int)] = {
    ventCoordinates.flatMap {
      case List(x1, y1, x2, y2) if x1 == x2 => Some(Range(y1.min(y2), y1.max(y2) + 1).toList.map((x1, _)))
      case List(x1, y1, x2, y2) if y1 == y2 => Some(Range(x1.min(x2), x1.max(x2) + 1).toList.map((_, y1)))
      case _ => None
    }.flatten
  }

  def fillCoordinatesWithDiagonals(ventCoordinates: List[List[Int]]): List[(Int, Int)] = {
    ventCoordinates.flatMap {
      case List(x1, y1, x2, y2) if x1 == x2 => Some(Range(y1.min(y2), y1.max(y2) + 1).toList.map((x1, _)))
      case List(x1, y1, x2, y2) if y1 == y2 => Some(Range(x1.min(x2), x1.max(x2) + 1).toList.map((_, y1)))
      case List(x1, y1, x2, y2) if x1 > x2 && y1 < y2 =>
        val xs = Range(x2, x1+1).toList
        val ys = Range(y1, y2+1).toList.reverse
        Some(xs.zip(ys).map(x => (x._1, x._2)))
      case List(x1, y1, x2, y2) if x1 < x2 && y1 > y2 =>
        val xs = Range(x1, x2+1).toList
        val ys = Range(y2, y1+1).toList.reverse
        Some(xs.zip(ys).map(x => (x._1, x._2)))
      case List(x1, y1, x2, y2) if x1 < x2 && y1 < y2 =>
        val xs = Range(x1, x2+1).toList
        val ys = Range(y1, y2+1).toList
        Some(xs.zip(ys).map(x => (x._1, x._2)))
      case List(x1, y1, x2, y2) if x1 > x2 && y1 > y2 =>
        val xs = Range(x2, x1+1).toList
        val ys = Range(y2, y1+1).toList
        Some(xs.zip(ys).map(x => (x._1, x._2)))
      case _ => None
    }.flatten
  }

  @tailrec
  def updateCoordinateSystem(cs: Map[(Int, Int), Int], updates: List[(Int, Int)]): Map[(Int, Int), Int] = {
    updates match {
      case ::(head, tail) =>
        val updatedValue = cs(head) + 1
        val updatedCs = cs + (head -> updatedValue)
        updateCoordinateSystem(updatedCs, tail)
      case Nil => cs
    }
  }
}
