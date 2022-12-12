package model.command

/**
 * Created 12.12.2022
 * @author Nejc Kozlevčar
 */

class NoopCommand: Command {
    override val cycles: Int = 1
    override val name: String = "noop"
    override fun execute(): Int {
        return 0
    }
}