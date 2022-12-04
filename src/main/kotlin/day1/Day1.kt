package day1

import model.Elf
import readElvesWithCalories


/**
 * Created 01.12.2022
 * @author Nejc Kozlevčar
 */

fun main() {
    val elves: List<Elf> = readElvesWithCalories("day1/input.txt")
//    1. task
    println(elves.maxOfOrNull { elf: Elf -> elf.sumCalories() })
//    2. task
    println(elves.map { elf: Elf -> elf.sumCalories() }.sortedDescending())
}