package model.command

/**
 * Created 12.12.2022
 * @author Nejc Kozlevčar
 */
class AddxCommand(private val x: Int) : Command {
    override val cycles: Int = 2
    override val name: String = "addx"
    override fun execute(): Int {
        return x;
    }
}