fun main() {
    fun part1(input: List<List<Int>>): Int {
        val height = input.size
        val width = input.first().size

        fun isVisibleFromOutside(row: Int, column: Int, direction: Direction): Boolean {
            val treeHeight = input[row][column]
            return input.treesByDirection(row, column, direction).all { it < treeHeight }
        }

        var count = (height + width - 2) * 2

        (1 until height - 1).forEach { row ->
            (1 until width - 1)
                .asSequence()
                .filter { column ->
                    Direction.values().any { isVisibleFromOutside(row, column, it) }
                }
                .forEach { count++ }
        }

        return count
    }


    fun part2(input: List<List<Int>>): Int {
        val height = input.size
        val width = input.first().size

        fun countVisibleTrees(r: Int, c: Int, direction: Direction): Int {
            val targetTree = input[r][c]
            var count = 0
            input.treesByDirection(r, c, direction).forEach { currentTree ->
                count++
                if (currentTree >= targetTree) return count
            }
            return count
        }

        var maxScore = 0

        (1 until height - 1).forEach { row ->
            (1 until width - 1)
                .asSequence()
                .map { column ->
                    Direction.values().map { countVisibleTrees(row, column, it) }.reduce(Int::times)
                }
                .forEach { maxScore = maxOf(it, maxScore) }
        }

        return maxScore
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test").toMatrix()
    part1(testInput)
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08").toMatrix()
    println(part1(input))
    println(part2(input))
}

private fun List<String>.toMatrix() =
    map { line -> line.map { Character.getNumericValue(it) } }

enum class Direction(val dr: Int = 0, val dc: Int = 0) {
    UP(dr = -1),
    LEFT(dc = -1),
    DOWN(dr = +1),
    RIGHT(dc = +1);

    fun next(coordinates: Pair<Int, Int>): Pair<Int, Int> {
        val (row, column) = coordinates
        return (row + dr) to (column + dc)
    }
}

private fun List<List<Int>>.treesByDirection(
    row: Int,
    column: Int,
    direction: Direction,
): Sequence<Int> =
    generateSequence(direction.next(row to column), direction::next)
        .takeWhile { (r, c) -> r in indices && c in first().indices }
        .map { (r, c) -> this[r][c] }
