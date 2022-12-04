fun main() {
    fun part1(input: List<List<IntRange>>): Int = input.filter { (first, second) ->
        first.all { it in second } || second.all { it in first }
    }.size

    fun part2(input: List<List<IntRange>>): Int = input.filter { (first, second) ->
        first.any { it in second }
    }.size

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test").toSectionsRange()
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04").toSectionsRange()
    println(part1(input))
    println(part2(input))
}

private fun List<String>.toSectionsRange(): List<List<IntRange>> = map { sections ->
    sections.split(",").map { section ->
        val (start, end) = section.split("-").map { it.toInt() }
        start..end
    }
}




