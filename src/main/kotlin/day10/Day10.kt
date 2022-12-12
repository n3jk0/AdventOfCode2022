package day10

import model.command.AddxCommand
import model.command.Command
import model.command.NoopCommand
import readFileByLine
import kotlin.math.abs

/**
 * Created 12.12.2022
 * @author Nejc Kozlevčar
 */

fun main() {
    val lines = readFileByLine("src/main/kotlin/day10/input.txt")
    var cycle: Int = 0
    var x: Int = 1

    /**
     * Commented code is solution for first star
     */
//    var signalSum: Int = 0
    
    var currentCRT: String = ""

    for (line in lines) {
        val (inst, num) = "(\\w+)([ -]*\\d*)".toRegex().find(line)!!.destructured
        val command: Command = when (inst) {
            "noop" -> NoopCommand()
            "addx" -> AddxCommand(num.trim().toInt())
            else -> NoopCommand()
        }

        for (i in 1..command.cycles) {
            currentCRT += if (isSpriteVisible(cycle % 40, x)) "#" else "."
            
            cycle++
            if (cycle % 40 == 0) {
//                println("----Cycle: $cycle, X: $x")
//                signalSum += cycle * x
                currentCRT+= "\n"
            }
        }
        x += command.execute()
    }
//    println("Sum: $signalSum")
    println(currentCRT)
}

fun isSpriteVisible(cycle: Int, x: Int): Boolean {
    return abs(x - cycle) <= 1 
}
