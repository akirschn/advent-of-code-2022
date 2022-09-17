package aoc

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Day10 {

  trait Operation
  case class Noop(cycles: Int = 1) extends Operation
  case class AddX(value: Int, cycles: Int = 2) extends Operation

  def parse(line: String): Operation =
    line.trim match {
      case "noop"                                  => new Noop
      case operation if operation.contains("addx") => AddX(operation.split(" ")(1).toInt)
    }

  @tailrec
  def execute(operations: List[Operation], x: List[Int] = List { 1 }): List[Int] =
    operations match {
      case Noop(1) :: next                             => execute(next, x :+ x.last)
      case AddX(value, cycles) :: next if (cycles > 1) => execute(AddX(value, cycles - 1) :: next, x :+ x.last)
      case AddX(value, 1) :: next                      => execute(next, x :+ x.last + value)
      case _ =>
        x
    }

  def solvePartOne(input: List[String]): Int = {
    val operations = input.map(parse)
    val xes = execute(operations)
    List(20, 60, 100, 140, 180, 220).map(position => position * xes(position - 1)).sum
  }

  def spriteVisibleAtPos(crtPosAtCycle: Int, spritePosAtCycle: Int): Char =
    (crtPosAtCycle, spritePosAtCycle) match {
      case (c, s) if c == s     => '#'
      case (c, s) if c == s - 1 => '#'
      case (c, s) if c == s + 1 => '#'
      case _                    => '.'
    }

  def toPrintableRow(cycles: List[Int], xes: List[Int]): String = {
    val buffer = new ListBuffer[Char]
    for ((col, cyc) <- Range.inclusive(1, 40).zip(cycles)) {
      val char = spriteVisibleAtPos(col - 1, xes(cyc - 1))
      buffer += char
    }
    buffer.mkString
  }

  def solvePartTwo(input: List[String]): Int = {
    val operations = input.map(parse)
    val xes = execute(operations)

    val cycles = Range.inclusive(1, 240).toList.grouped(40).toList
    val rows = cycles.map(c => toPrintableRow(c, xes))

    for (row <- rows) {
      println(row)
    }

    println("--------")

    0
  }
}
