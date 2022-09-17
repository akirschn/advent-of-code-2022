package aoc

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object Day08 {

  case class Tree(x: Int, y: Int, height: Int)

  def parseInput(input: List[String]): List[Tree] = {

    val treeBuffer = new ListBuffer[Tree]
    val inputReversed = input.reverse

    for (y <- Range.inclusive(0, input.length - 1)) {
      val heights = inputReversed(y).toList
      for (x <- Range.inclusive(0, input.head.length - 1)) {
        treeBuffer.addOne(Tree(x, y, heights(x).asDigit))
      }
    }

    treeBuffer.toList
  }

  def blocked(tree: Tree, trees: List[Tree]): Boolean = {

    val fromTop = trees.count(t => t.x == tree.x && t.y > tree.y && t.height >= tree.height) > 0
    val fromBottom = trees.count(t => t.x == tree.x && t.y < tree.y && t.height >= tree.height) > 0
    val fromLeft = trees.count(t => t.y == tree.y && t.x < tree.x && t.height >= tree.height) > 0
    val fromRight = trees.count(t => t.y == tree.y && t.x > tree.x && t.height >= tree.height) > 0

    fromTop && fromBottom && fromLeft && fromRight
  }

  def scenicScore(tree: Tree, trees: List[Tree]): Int = {

    val left = visibleInTreeline(tree, trees.filter(t => t.y == tree.y && t.x < tree.x).reverse)
    val right = visibleInTreeline(tree, trees.filter(t => t.y == tree.y && t.x > tree.x))
    val top = visibleInTreeline(tree, trees.filter(t => t.x == tree.x && t.y > tree.y))
    val bottom = visibleInTreeline(tree, trees.filter(t => t.x == tree.x && t.y < tree.y).reverse)

    left * right * top * bottom
  }

  @tailrec
  def visibleInTreeline(tree: Tree, treeLine: List[Tree], counter: Int = 0): Int =
    treeLine match {
      case ::(head, tail) if head.height < tree.height => visibleInTreeline(tree, tail, counter + 1)
      case ::(head, _) if head.height >= tree.height   => counter + 1
      case _                                           => counter
    }

  def solvePartOne(input: List[String]): Int = {
    val trees = parseInput(input)
    trees.count(blocked(_, trees) == false)
  }

  def solvePartTwo(input: List[String]): Int = {
    val trees = parseInput(input)
    trees.map(scenicScore(_, trees)).max
  }
}
