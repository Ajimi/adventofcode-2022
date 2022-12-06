data class Action(val amount: Int, val from: Int, val to: Int)

fun main() {
    fun performOperations(
        actions: List<Action>,
        stacks: List<MutableList<Char>>,
        shouldReverse: Boolean = true,
    ) {
        actions.forEach { (amount, from, to) ->
            val toAdd = stacks[from].take(amount)
            repeat(amount) { stacks[from].removeFirst() }
            stacks[to].addAll(0, if (shouldReverse) toAdd.reversed() else toAdd)
        }
    }

    fun part1(input: List<String>): String {
        val (first, second) = input
        val stacks = first.toStacks()
        val actions = second.toAction()
        performOperations(actions, stacks)

        return stacks.topStack()
    }

    fun part2(input: List<String>): String {
        val (first, second) = input
        val stacks = first.toStacks()
        val actions = second.toAction()
        performOperations(actions, stacks, false)

        return stacks.topStack()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTextInput("Day05_test").split("\n\n")


    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readTextInput("Day05").split("\n\n")
    println(part1(input))
    println(part2(input))
}


private fun List<List<Char>>.topStack(): String =
    map { it.first() }.joinToString("")

private fun String.toStacks(): List<MutableList<Char>> {
    val stackRows = split("\n").takeWhile { it.contains('[') }
    return (1..stackRows.last().length step 4).map { index ->
        stackRows
            .mapNotNull { it.getOrNull(index) }
            .filter { it.isUpperCase() }
            .toMutableList()
    }
}

private fun String.toAction(): List<Action> {
    return split("\n")
        .map { action ->
            action.split(" ")
                .mapNotNull { it.toIntOrNull() }
        }
        .dropLast(1)
        .map { (amount, from, to) ->
            Action(amount, from - 1, to - 1)
        }
}





