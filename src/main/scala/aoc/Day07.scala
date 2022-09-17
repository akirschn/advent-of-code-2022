package aoc

import scala.annotation.tailrec

object Day07 {

  trait Node {
    def path: String
  }

  case class Directory(path: String)
    extends Node
  case class File(path: String, size: Int) extends Node

  @tailrec
  def parseLines(
      lines: List[String],
      currentPathElements: List[String] = List.empty,
      nodes: List[Node] = List.empty
  ): List[Node] = {

    val currentPath = currentPathElements match {
      case Nil => "/"
      case _   => "/" + currentPathElements.mkString("/") + "/"
    }

    lines match {
      case ::(head, tail) =>
        head match {
          case "$ cd .."                        => parseLines(tail, currentPathElements.dropRight(1), nodes)
          case "$ cd /"                         => parseLines(tail, List.empty, nodes)
          case line if line.startsWith("$ cd")  => parseLines(tail, currentPathElements :+ line.split(" ")(2), nodes)
          case line if line.startsWith("dir ")  => parseLines(tail, currentPathElements, nodes :+ Directory(currentPath + line.split(" ")(1) + "/"))
          case line if line.toList.head.isDigit => parseLines(tail, currentPathElements, nodes :+ File(currentPath, line.split(" ")(0).toInt))
          case _                                => parseLines(tail, currentPathElements, nodes)
        }
      case Nil => nodes
    }
  }

  def calculateDirectorySize(directory: Directory, allFilesInFilesystem: List[File]): Int =
    allFilesInFilesystem.filter(file => file.path.startsWith(directory.path)).map(_.size).sum

  def parse(input: List[String]): (List[Directory], List[File]) = {
    val nodes = parseLines(input)
    val directories = nodes.collect { case n: Directory => n }
    val files = nodes.collect { case n: File => n }
    (directories, files)
  }

  def solvePartOne(input: List[String]): Int = {
    val (directories, files) = parse(input)
    directories.map(calculateDirectorySize(_, files)).filter(_ < 100000).sum
  }

  def solvePartTwo(input: List[String]): Int = {
    val (directories, files) = parse(input)
    val directorySizes = directories.map(calculateDirectorySize(_, files))

    val usedSpace = calculateDirectorySize(Directory("/"), files)
    val freeSpace = 70000000 - usedSpace
    val neededSpace = 30000000 - freeSpace

    directorySizes.filter(_ > neededSpace).min
  }
}
