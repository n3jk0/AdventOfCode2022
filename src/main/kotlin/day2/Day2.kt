package day2

import readFileByLine

/**
 * Created 02.12.2022
 * @author Nejc Kozlevčar
 */
val shapeScore: Map<String, Int> = mapOf("A" to 1, "B" to 2, "C" to 3, "X" to 1, "Y" to 2, "Z" to 3)

/**
 * Task: https://adventofcode.com/2022/day/2
 */
fun main() {
    val lines = readFileByLine("day2/input.txt")
    var score: Int = 0
    for (line in lines) {
        if (line.length != 3) {
            println("Something wrong in line: $line!")
            break
        }
        val first = shapeScore.getOrDefault(line[0].toString(), 0)
        var second = shapeScore.getOrDefault(line[2].toString(), 0)

        // Second star
        when (second) {
            // WIN
            1 -> second = if (first + 2 > 3) first - 1 else first + 2
            // DRAW
            2 -> second = first
            // LOSE
            3 -> second = if (first + 1 > 3) first - 2 else first + 1
        }

        // Shape score
        score += second
        if (first == second) {
            // Draw
            score += 3
        } else if ((second == 1 && first == 3) || (second == 2 && first == 1) || (second == 3 && first == 2)) {
            // Win
            score += 6
        }
        // Lose = 0
    }
    println(score)
}