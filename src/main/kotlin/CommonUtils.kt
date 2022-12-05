import model.Elf
import model.Stack
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

fun readStacksOfCreates(fileName: String): Map<Int, Stack> {
    val lines: List<String> = readFileByLine(fileName)
    val lineSize: Int = lines[0].length
    val stacksById: MutableMap<Int, Stack> = mutableMapOf()
    for (line in lines) {
        var id: Int = 0
        if (line.isEmpty()) {
            break
        }
        for (i in 1..lineSize step 4) {
            val crate = line[i]
            id += 1
            if (crate == ' ' || crate in '0'..'9') {
                continue
            }
            stacksById.putIfAbsent(id, Stack(id, mutableListOf()))
            val stack = stacksById[id]
            stack?.crates?.add(crate.toString())
        }
    }

    return stacksById
}