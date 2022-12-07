package day7

import model.Directory
import model.File
import readFileByLine

/**
 * Created 07.12.2022
 * @author Nejc Kozlevčar
 */

fun main() {
    val TOTAL_SPACE = 70000000
    val REQUIRED_SPACE = 30000000
//    val lines = readFileByLine("src/main/kotlin/day7/smallInput.txt")
    val lines = readFileByLine("src/main/kotlin/day7/input.txt")
    // First star
    val root = createFileSystemFromTerminal(lines)
    println(sumDirectorySize(root, 100000))
    // Second star
    println("Current space: " + (TOTAL_SPACE - root.size()))
    println("Must remove: " + (root.size() - (TOTAL_SPACE - REQUIRED_SPACE)))
    println(getSmallestToDelete(root, (root.size() - (TOTAL_SPACE - REQUIRED_SPACE))))

}

fun createFileSystemFromTerminal(lines: List<String>): Directory {
    val rootDirectory: Directory = Directory("/", null)
    var currentDirectory: Directory = rootDirectory

    for (line in lines) {
        // New command
        if (line.startsWith("\$")) {
            if (line == "\$ ls") {
                continue
            } else if (line.startsWith("\$ cd"))
            currentDirectory = when (val newDir: String = line.replace("\$ cd ", "")) {
                "/" -> rootDirectory
                ".." -> currentDirectory.parent!!
                else -> currentDirectory.getDirectory(newDir)!!
            }
            continue
        }
        // Output
        val split = line.split(" ")

        when (split[0]) {
            "dir" -> currentDirectory.directories.add(Directory(split[1], currentDirectory))
            else -> currentDirectory.files.add(File(split[1], split[0].toInt()))
        }
    }

    return rootDirectory
}

fun sumDirectorySize(rootDirectory: Directory, maxSize: Int): Int {
    return rootDirectory.allDirectories().map { directory: Directory -> directory.size() }.filter { i -> i < maxSize }.sum()
}

fun getSmallestToDelete(rootDirectory: Directory, sizeToDelete: Int): Int {
    return rootDirectory.allDirectories().map { directory: Directory -> directory.size() }.filter { i -> i > sizeToDelete }.minOf { it }
}