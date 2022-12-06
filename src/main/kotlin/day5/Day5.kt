package day5

import model.Stack
import readFileByLine
import readStacksOfCreates

/**
 * Created 05.12.2022
 * @author Nejc Kozlevčar
 */

/**
 * Task: https://adventofcode.com/2022/day/5
 */
fun main() {
    var stacks = readStacksOfCreates("src/main/kotlin/day5/input.txt")
    val moves = readFileByLine("src/main/kotlin/day5/input.txt").filter { s -> s.startsWith("move") }

    // First star
    moves.forEach { move -> makeMove9000(move, stacks) }
    println(getTopCrates(stacks))
    
    // Second star
    stacks = readStacksOfCreates("src/main/kotlin/day5/input.txt")

    moves.forEach { move -> makeMove9001(move, stacks) }
    println(getTopCrates(stacks))
}

fun makeMoves(stackFrom: Stack?, stackTo: Stack?, size: Int = 1) {
    if (stackFrom == null || stackTo == null) {
        return
    }

    val crateToMove = stackFrom.crates.subList(0, size)
    stackTo.crates.addAll(0, crateToMove)
    stackFrom.dropCrate(size)
}

fun makeMove9000(move: String, stacks: Map<Int, Stack>) {
    val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
    val (size, from, to) = regex.find(move)!!.destructured
    for (i in 1..size.toInt()) {
        makeMoves(stacks[from.toInt()], stacks[to.toInt()])
    }
}

fun makeMove9001(move: String, stacks: Map<Int, Stack>) {
    val regex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
    val (size, from, to) = regex.find(move)!!.destructured
    makeMoves(stacks[from.toInt()], stacks[to.toInt()], size.toInt())
}

fun getTopCrates(stacks: Map<Int, Stack>) = stacks.values.sortedBy { stack -> stack.id }.joinToString("") { stack -> stack.firstCrate() }