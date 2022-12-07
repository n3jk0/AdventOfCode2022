package model

/**
 * Created 07.12.2022
 * @author Nejc Kozlevčar
 */
class Directory(
    private val name: String,
    val parent: Directory?,
    var files: MutableList<File> = mutableListOf(),
    var directories: MutableList<Directory> = mutableListOf()
) {
    private fun filesSize() = files.sumOf { file -> file.size }
    
    fun size(): Int = filesSize() + directories.sumOf { directory: Directory -> directory.size() }

    fun getDirectory(name: String): Directory? = directories.find { directory -> directory.name == name }
    
    fun allDirectories(): Set<Directory> = directories.union(directories.flatMap { directory: Directory -> directory.allDirectories() })
    
    fun tree(level: Int = 0): String {
        return "  ".repeat(level) + "- $name (dir) (size: ${size()})\n" + directories.joinToString("") { directory -> directory.tree(level + 1) } + files.joinToString("") { file -> file.tree(level + 1) }
    }

    override fun toString(): String {
        return "Directory(name='$name', size='${size()}')"
    }


}