package model.command

/**
 * Created 12.12.2022
 * @author Nejc Kozlevčar
 */
interface Command {
    val name: String
    val cycles: Int
    
    fun execute(): Int
}