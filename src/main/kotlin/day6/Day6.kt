package day6

import readFirstLine

/**
 * Created 06.12.2022
 * @author Nejc Kozlevčar
 */


/**
 * Task: https://adventofcode.com/2022/day/6
 */
fun main() {
    val dataStream = readFirstLine("src/main/kotlin/day6/input.txt")
    // First Star
    println(findFirstMarker(dataStream))
    // Second Star
    println(findFirstMarker(dataStream, 14))
}

fun findFirstMarker(dataStream: String, distinctSize: Int = 4): Int {
    for (i in distinctSize..dataStream.length) {
        val chunk = dataStream.substring(i - distinctSize, i)
        if (!anyDuplicate(chunk)) {
            return i
        }
    }
    return -1
}

fun anyDuplicate(chunk: String) = chunk.toCharArray().size != chunk.toCharArray().distinct().count()