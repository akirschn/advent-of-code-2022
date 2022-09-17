package aoc

object Day11 {

  case class Monkey(
      index: Int = -1,
      items: List[BigInt] = List.empty,
      operation: (BigInt => BigInt) = (x => x),
      divBy: Int = -1,
      trueTarget: Int = -1,
      falseTarget: Int = -1,
      numInspected: BigInt = 0L
  )

  private def parseMonkeys(input: List[String]): List[Monkey] = {

    def parseMonkey(line: String, monkey: Monkey): Monkey =
      line match {
        case l if l.startsWith("Monkey") =>
          val index = l.split(" ")(1).dropRight(1).toInt
          monkey.copy(index = index)
        case l if l.contains("Starting items") =>
          val items = l.split(":")(1).split(",").map(_.trim).map(BigInt(_)).toList
          monkey.copy(items = items)
        case l if l.contains("Test") =>
          val divBy = l.split("divisible by")(1).trim.toInt
          monkey.copy(divBy = divBy)
        case l if l.contains("Operation") =>
          l match {
            case op if op.contains("old * old") =>
              monkey.copy(operation = x => x * x)
            case op if op.contains("*") =>
              val operand = op.split(" ").last.trim.toInt
              monkey.copy(operation = x => x * operand)
            case op if op.contains("+") =>
              val operand = op.split(" ").last.trim.toInt
              monkey.copy(operation = x => x + operand)
            case op => throw new RuntimeException(s"Missing operator handling $op")
          }
        case l if l.contains("If true") =>
          val trueTarget = l.split("monkey")(1).trim.toInt
          monkey.copy(trueTarget = trueTarget)
        case l if l.contains("If false") =>
          val falseTarget = l.split("monkey")(1).trim.toInt
          monkey.copy(falseTarget = falseTarget)
        case l => throw new RuntimeException(l)
      }

    val monkeyStartIndexes = input.zipWithIndex.filter(_._1.startsWith("Monkey")).map(_._2)
    val monkeyLines = monkeyStartIndexes.map(msi => input.slice(msi, msi + 6))
    val monkeys = monkeyLines.map { lines =>
      var monkey = Monkey()
      for (line <- lines) {
        monkey = parseMonkey(line, monkey)
      }
      monkey
    }
    monkeys
  }

  private def doMonkeyRound(monkey: Monkey, monkeys: List[Monkey], reliefFactor: Int): List[Monkey] = {

    var updatedMonkeys = monkeys
    val modulus = monkeys.map(_.divBy).product

    for (item <- monkey.items) {
      val updatedItem = (monkey.operation(item) / reliefFactor) % modulus
      val targetIndex = if ((updatedItem % monkey.divBy) == 0) {
        monkey.trueTarget
      } else {
        monkey.falseTarget
      }
      val target = updatedMonkeys(targetIndex)
      val updatedTarget = target.copy(items = target.items :+ updatedItem)
      updatedMonkeys = updatedMonkeys.updated(targetIndex, updatedTarget)
    }

    updatedMonkeys.updated(monkey.index, monkey.copy(items = List.empty, numInspected = monkey.numInspected + monkey.items.length))
  }

  private def doMonkeyRounds(monkeys: List[Monkey], numRounds: Int, reliefFactor: Int): List[Monkey] = {
    var updatedMonkeys = monkeys

    for (_ <- Range.inclusive(1, numRounds)) {
      for (index <- Range.inclusive(0, monkeys.length - 1)) {
        updatedMonkeys = doMonkeyRound(updatedMonkeys(index), updatedMonkeys, reliefFactor)
      }
    }

    updatedMonkeys
  }

  def solvePartOne(input: List[String]): BigInt = {
    val monkeys = parseMonkeys(input)
    val updatedMonkeys = doMonkeyRounds(monkeys, 20, 3)
    updatedMonkeys.map(_.numInspected).sorted.reverse.take(2).product
  }

  def solvePartTwo(input: List[String]): BigInt = {
    val monkeys = parseMonkeys(input)
    val updatedMonkeys = doMonkeyRounds(monkeys, 10000, 1)
    updatedMonkeys.map(_.numInspected).sorted.reverse.take(2).product
  }
}
