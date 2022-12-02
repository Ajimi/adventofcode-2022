fun main() {
    fun part1(input: List<Pair<Int, Int>>) = input.sumOf { (opponent, me) ->
        me + 1 + when (me) {
            (opponent + 1) % 3 -> 6
            opponent -> 3
            else -> 0
        }
    }

    fun part2(input: List<Pair<Int, Int>>): Int = input.sumOf { (opponent, me) ->
        me * 3 + when (me) {
            0 -> (opponent + 2) % 3
            1 -> opponent
            else -> (opponent + 1) % 3
        } + 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test").parse()
    check(part1(testInput) == 15)

    val input = readInput("Day02").parse()
    println(part1(input))
    println(part2(input))
}

private infix fun Int.score(me: Int): Int {
    val difference = this - me
    return when {
        difference > 0 -> 0
        difference == 0 -> 3
        else -> 6
    }
}

private fun List<String>.parse(): List<Pair<Int, Int>> = map {
    it.split(" ")
}.map { (first, second) ->
    first.first() - 'A' to second.first() - 'X'
}
