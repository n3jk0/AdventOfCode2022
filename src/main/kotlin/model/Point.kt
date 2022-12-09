package model

/**
 * Created 09.12.2022
 * @author Nejc Kozlevčar
 */
data class Point(var x: Int, var y: Int) {
    fun right() = x++
    fun left() = x--
    fun up() = y++
    fun down() = y--
}