package model

/**
 * Created 01.12.2022
 * @author Nejc Kozlevčar
 */
class Elf(private var id: Int, private var calories: List<Int>) {
    fun sumCalories(): Int = calories.sum()
}