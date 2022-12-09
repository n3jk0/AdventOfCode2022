package day9

import model.Point
import readFileByLine
import kotlin.math.abs

/**
 * Created 09.12.2022
 * @author Nejc Kozlevčar
 */

fun main() {
//    val lines = readFileByLine("src/main/kotlin/day9/smallInput.txt")
    val lines = readFileByLine("src/main/kotlin/day9/input.txt")

    val smallRope: List<Point> = listOf(Point(0, 0), Point(0, 0))
    val smallTrace: MutableSet<Point> = mutableSetOf()

    // First star
    lines.forEach { line -> performMotion(smallRope, line, smallTrace) }
    println("Small trace count: ${smallTrace.size}")
    // Second star
    val largeRope: List<Point> =
        listOf(Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0), Point(0, 0))
    val largeTrace: MutableSet<Point> = mutableSetOf()
    lines.forEach { line -> performMotion(largeRope, line, largeTrace) }
    println("Large trace count: ${largeTrace.size}")
}

fun performMotion(rope: List<Point>, move: String, trace: MutableSet<Point>) {
    val (direction, num) = "([RLUD]) (\\d+)".toRegex().find(move)!!.destructured
    val windowed = rope.windowed(2)

    for (i in 1..num.toInt()) {
        movePoint(rope.first(), direction)
        windowed.forEach { pair -> moveTailToHead(pair[0], pair[1]) }
        trace.add(rope.last().copy())
    }
}

fun movePoint(point: Point, direction: String) {
    when (direction) {
        "R" -> point.right()
        "L" -> point.left()
        "U" -> point.up()
        "D" -> point.down()
    }

}

fun moveTailToHead(headPoint: Point, tailPoint: Point) {
    val differenceX = headPoint.x - tailPoint.x
    val differenceY = headPoint.y - tailPoint.y
    if (abs(differenceX) <= 1 && abs(differenceY) <= 1) {
        // Correct position
        return
    }

    if (differenceY > 0) {
        movePoint(tailPoint, "U")
    } else if (differenceY < 0) {
        movePoint(tailPoint, "D")
    }

    if (differenceX > 0) {
        movePoint(tailPoint, "R")
    } else if (differenceX < 0) {
        movePoint(tailPoint, "L")
    }
}