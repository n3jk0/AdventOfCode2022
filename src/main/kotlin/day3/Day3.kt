package day3

import readFileByLine

/**
 * Created 04.12.2022
 * @author Nejc Kozlevčar
 */

/**
 * Task: https://adventofcode.com/2022/day/3
 */
fun main() {
    val lines = readFileByLine("src/main/kotlin/day3/input.txt")
    // First star
    println(lines.sumOf { line -> findSharedItemPriority(line) })
    // Second star
    println(lines.chunked(3).sumOf { group -> findSharedItemPriorityAsGroup(group) })

}

fun separateRucksack(rucksack: String): Pair<String, String> =
    Pair(rucksack.substring(0, rucksack.length / 2), rucksack.substring(rucksack.length / 2))

fun findSharedItem(compartments: List<String>): Char {
    val intersect = compartments.fold(compartments[0]) { compartment, curr ->
        curr.toCharArray().intersect(compartment.toCharArray().asIterable().toSet()).toString()
    }
    if (intersect.length != 3) {
        println("Something wrong in rucksack: $intersect!")
        return '!'
    }
    return intersect[1]
}

fun findSharedItemPriority(rucksack: String): Int {
    val item = findSharedItem(separateRucksack(rucksack).toList())
    return if (item.isLowerCase()) (item.code - 'a'.code) + 1 else (item.code - 'A'.code) + 27
}

fun findSharedItemPriorityAsGroup(rucksacks: List<String>): Int {
    val item = findSharedItem(rucksacks)
    return if (item.isLowerCase()) (item.code - 'a'.code) + 1 else (item.code - 'A'.code) + 27
}