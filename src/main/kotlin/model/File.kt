package model

/**
 * Created 07.12.2022
 * @author Nejc Kozlevčar
 */
class File(private val name: String, val size: Int) {
    fun tree(level: Int = 0) = "  ".repeat(level) + "- $name (file, size=$size)\n"
}