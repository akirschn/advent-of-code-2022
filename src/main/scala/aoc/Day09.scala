package aoc

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Day09 {

  case class Move(direction: String, steps: Int)
  case class Position(x: Int, y: Int)

  def solvePartOne(input: List[String]): Int = {
    val snake = List(Position(0, 0), Position(0, 0))
    solve(input, snake)
  }

  def solvePartTwo(input: List[String]): Int = {
    val head = Position(0, 0)
    val tail = List.fill(9)(Position(0, 0))
    val snake = List(head) ++ tail
    solve(input, snake)
  }

  def solve(input: List[String], snake: List[Position]): Int = {
    val multiMoves = input.map(_.splitAt(1)).map(s => Move(s._1, s._2.trim.toInt))
    val moves = multiMoves.flatMap(m => List.fill(m.steps)(Move(m.direction, 1)))
    var currentSnake = snake
    val tailPositions = new ListBuffer[Position]

    for (move <- moves) {
      currentSnake = processMove(move, currentSnake)
      tailPositions += currentSnake.last
    }

    tailPositions.distinct.length
  }

  @tailrec
  def processMove(move: Move, snake: List[Position], updatedSnake: List[Position] = List.empty): List[Position] = {

    snake match {

      case ::(head, next) =>
        if (updatedSnake.isEmpty) {

          val updatedHead = move.direction match {
            case "U" => head.copy(y = head.y + 1)
            case "L" => head.copy(x = head.x - 1)
            case "R" => head.copy(x = head.x + 1)
            case "D" => head.copy(y = head.y - 1)
          }

          processMove(move, next, updatedSnake :+ updatedHead)

        } else {

          val updatedTail = followHead(updatedSnake.last, head)
          processMove(move, next, updatedSnake :+ updatedTail)
        }

      case _ => updatedSnake
    }
  }

  def followHead(head: Position, tail: Position): Position = {

    val distX = head.x - tail.x
    val distY = head.y - tail.y

    if (distX.abs > 1 || distY.abs > 1) {

      val x = distX match {
        case dist if dist > 0  => tail.x + 1
        case dist if dist == 0 => tail.x
        case _                 => tail.x - 1
      }

      val y = distY match {
        case dist if dist > 0  => tail.y + 1
        case dist if dist == 0 => tail.y
        case _                 => tail.y - 1
      }

      Position(x, y)

    } else tail
  }
}
