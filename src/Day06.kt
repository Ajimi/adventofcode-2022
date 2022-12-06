fun main() {
    fun String.packetStart(length: Int): Int = windowed(length)
        .indexOfFirst { it.toSet().size == length } + length

    fun part1(input: String): Int = input.packetStart(4)

    fun part2(input: String): Int = input.packetStart(14)

    // test if implementation meets criteria from the description, like:
    val testInput = readTextInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readTextInput("Day06")
    println(part1(input))
    println(part2(input))
}



