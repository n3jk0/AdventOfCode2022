package day8

import readFileByLine

/**
 * Created 08.12.2022
 * @author Nejc Kozlevčar
 */

fun main() {
     val lines = readFileByLine("src/main/kotlin/day8/input.txt")
//    val lines = readFileByLine("src/main/kotlin/day8/smallInput.txt")
    val treeGrid = lines.map { line -> line.toCharArray().map { it.digitToInt() } }

    // First star   
    val n = treeGrid.size
    var visibleCount: Int = 0

    for (i in 0 until n) {
        for (j in 0 until n) {
            val isTreeVisible = isTreeVisible(treeGrid, i, j)
            visibleCount += isTreeVisible.toInt()
        }
    }
    println("Visible: $visibleCount")

    // Second star
    var max: Int = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            val countVisibleTrees = countVisibleTrees(treeGrid, i, j)
            if (countVisibleTrees > max) {
                max = countVisibleTrees 
            }
        }
    }
    println("Scenic score: $max")

}

fun isTreeVisible(treeGrid: List<List<Int>>, i: Int, j: Int): Boolean {
    val n = treeGrid.size
    // Trees around the edge   
    if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
        return true
    }
    val tree = treeGrid[i][j]
    val row = (0 until n).map { treeGrid[it][j] }

    // Left || Right || Up || Down
    return treeGrid[i].subList(0, j).all { it < tree } || treeGrid[i].subList(j + 1, n).all { it < tree } || row.subList(0, i)
        .all { it < tree } || row.subList(i + 1, n).all { it < tree }
}

fun countVisibleTrees(treeGrid: List<List<Int>>, i: Int, j: Int): Int {
    val n = treeGrid.size
    // Trees around the edge   
    if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
        return 0
    }
    val tree = treeGrid[i][j]
    val row = (0 until n).map { treeGrid[it][j] }


    // Left * Right * Up * Down
    return scenicScore(treeGrid[i].subList(0, j).reversed(), tree) * scenicScore(treeGrid[i].subList(j + 1, n), tree) * scenicScore(row.subList(0, i).reversed(), tree) * scenicScore(row.subList(i + 1, n), tree)
}

fun scenicScore(list: List<Int>, max:Int): Int {
    val res = mutableListOf<Int>()
    for(i in list) {
        res.add(i)
        if (i >= max) {
            break
        }
    }
    return res.count()
}

fun Boolean.toInt() = if (this) 1 else 0