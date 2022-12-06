package day4

import readFileByLine


/**
 * Created 04.12.2022
 * @author Nejc Kozlevčar
 */

/**
 * Task: https://adventofcode.com/2022/day/4
 */
fun main() {
    val lines = readFileByLine("src/main/kotlin/day4/input.txt")
    // First star
    println(lines.map { line -> isFullyOverlap(line) }.count { it })
    // Second star
    println(lines.map { line -> isPartlyOverlap(line) }.count { it })
}

fun isFullyOverlap(line: String): Boolean {
    val (firstElf, secondElf) = Pair(line.substringBefore(",").trim(), line.substringAfter(",").trim())
    val (firstElfLower, firstElfHigher) = Pair(firstElf.substringBefore("-").toInt(), firstElf.substringAfter("-").toInt())
    val (secondElfLower, secondElfHigher) = Pair(secondElf.substringBefore("-").toInt(), secondElf.substringAfter("-").toInt())

    return (firstElfLower >= secondElfLower && firstElfHigher <= secondElfHigher) || (secondElfLower >= firstElfLower && secondElfHigher <= firstElfHigher)
}

fun isPartlyOverlap(line: String): Boolean {
    val (firstElf, secondElf) = Pair(line.substringBefore(",").trim(), line.substringAfter(",").trim())
    val (firstElfLower, firstElfHigher) = Pair(firstElf.substringBefore("-").toInt(), firstElf.substringAfter("-").toInt())
    val (secondElfLower, secondElfHigher) = Pair(secondElf.substringBefore("-").toInt(), secondElf.substringAfter("-").toInt())

    return (firstElfLower in secondElfLower..secondElfHigher) || (firstElfHigher in secondElfLower..secondElfHigher) ||
            (secondElfLower in firstElfLower..firstElfHigher) || (secondElfHigher in firstElfLower..firstElfHigher)
}