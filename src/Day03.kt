fun main() {
    fun Char.score() = if (isLowerCase()) {
        this - 'a' + 1
    } else {
        this - 'A' + 27
    }

    fun part1(input: List<String>): Int = input
        .map { it.chunked(it.length / 2) }
        .fold(listOf<Int>()) { acc, (first, second) ->
            first.forEach { character ->
                if (second.contains(character)) {
                    return@fold acc + character.score()
                }
            }
            acc
        }.sum()

    fun part2(input: List<String>) = input
        .chunked(3)
        .fold(listOf<Int>()) { acc, (first, second, third) ->
            first.forEach { character ->
                if (second.contains(character) && third.contains(character)) {
                    return@fold acc + character.score()
                }
            }
            acc
        }.sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
