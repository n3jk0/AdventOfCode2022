package model

/**
 * Created 05.12.2022
 * @author Nejc Kozlevčar
 */
class Stack(val id: Int, var crates: MutableList<String>) {
    override fun toString(): String {
        return "Stack(id: $id, crates: $crates)"
    }
    
    fun firstCrate() = crates.first()
    
    fun dropCrate(size: Int) {
        crates = crates.drop(size).toMutableList()
    }
}