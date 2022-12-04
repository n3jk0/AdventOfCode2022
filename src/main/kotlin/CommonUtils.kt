import model.Elf
import java.io.File

/**
 * Created 01.12.2022
 * @author Nejc Kozlevčar
 */
fun readFileByLine(fileName: String): List<String> = File(fileName).readLines()

fun readElvesWithCalories(fileName: String): List<Elf> {
    val elves: MutableList<Elf> = mutableListOf()
    val lines: List<String> = readFileByLine(fileName)
    var i = 0
    var calories: MutableList<Int> = mutableListOf()
    for (line in lines) {
        if (line.isEmpty()) {
            elves.add(Elf(i++, calories))
            calories = mutableListOf()
            continue
        }
        calories.add(line.toInt())
    }
    // Add last
    elves.add(Elf(i, calories))

    return elves
}