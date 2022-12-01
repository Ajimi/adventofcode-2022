fun main() {
    fun part1(input: List<String>): Int {
        return input.caloriesSum().max()
    }

    fun part2(input: List<String>): Int {
        return input.caloriesSum().sortedDescending().subList(0, 3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun List<String>.caloriesSum(): MutableList<Int> {
    val caloriesSumsByElf = mutableListOf<Int>()
    var caloriesCount = 0

    forEach { line ->
        if (line.isEmpty()) {
            caloriesSumsByElf.add(caloriesCount)
            caloriesCount = 0
        } else {
            caloriesCount += line.toInt()
        }
    }

    return caloriesSumsByElf
}
