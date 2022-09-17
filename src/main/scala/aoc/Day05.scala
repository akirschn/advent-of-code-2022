package aoc

object Day05 {
  case class Command(from: Int, to: Int, amount: Int)

  type Stack = List[List[Char]]

  def extractCommand(line: String): Command = {
    val parts = ("""\d+""".r findAllIn line).toList
    val from = parts(1).toInt
    val to = parts(2).toInt
    val amount = parts(0).toInt
    Command(from, to, amount)
  }

  def add(stack: Stack, position: Int, characters: List[Char]): Stack = {
    val zeroBasedPosition = position - 1
    val currentValue = stack(zeroBasedPosition)
    val newValue = currentValue ++ characters
    val newStack = stack.updated(zeroBasedPosition, newValue)
    newStack
  }

  def remove(stack: Stack, position: Int, amount: Int): (Stack, List[Char]) = {
    val zeroBasedPosition = position - 1
    val currentValue = stack(zeroBasedPosition)
    val newValue = currentValue.take(currentValue.length - amount)
    val newStack = stack.updated(zeroBasedPosition, newValue)
    val removedCharacters = currentValue.takeRight(amount)
    (newStack, removedCharacters)
  }

  def applyCommand(stack: Stack, command: Command): Stack = {
    if (command.amount > 0) {
      val (stack1, characters) = remove(stack, command.from, 1)
      val stack2 = add(stack1, command.to, characters)
      applyCommand(stack2, Command(command.from, command.to, command.amount - 1))
    } else {
      stack
    }
  }
  def applyCommandMultiple(stack: Stack, command: Command): Stack = {
    val (stack1, characters) = remove(stack, command.from, command.amount)
    add(stack1, command.to, characters)
  }

  def applyCommands(stack: Stack, commands: List[Command], moveOneByOne: Boolean = true): Stack = {
    commands match {
      case ::(head, tail) if moveOneByOne  => applyCommands(applyCommand(stack, head), tail)
      case ::(head, tail) if !moveOneByOne => applyCommands(applyCommandMultiple(stack, head), tail, false)
      case _                               => stack
    }
  }

  def solvePartOne(input: List[String], stack: List[List[Char]]): String = {
    val commands = input.filter(line => line.contains("move")).map(extractCommand)
    val modifiedStack = applyCommands(stack, commands)
    modifiedStack.map(stack => stack.last).mkString
  }

  def solvePartTwo(input: List[String], stack: List[List[Char]]): String = {
    val commands = input.filter(line => line.contains("move")).map(extractCommand)
    val modifiedStack = applyCommands(stack, commands, false)
    modifiedStack.map(stack => stack.last).mkString
  }
}
