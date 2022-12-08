typealias Path = String

fun main() {
    fun part1(input: List<String>): Long {
        return input.toDirSizes().values.filter { it <= 100000 }.sum()
    }

    fun part2(input: List<String>): Long {
        val sizeMap = input.toDirSizes()
        val sizeNeeded = 30000000 - (70000000 - sizeMap[""]!!)
        return sizeMap.values.filter { it >= sizeNeeded }.min()
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437L)
    check(part2(testInput) == 24933642L)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

private fun List<String>.toFullPath(): Path = this.joinToString("/")


fun List<String>.toDirSizes(): Map<String, Long> {
    val currentDir = mutableListOf<String>()
    val mapSize = mutableMapOf<Path, Long>()
    forEach { line ->
        when {
            line.startsWith("$ cd ") -> {
                when (line) {
                    "$ cd /" -> currentDir.clear()
                    "$ cd .." -> currentDir.removeLast()
                    else -> currentDir.add(line.removePrefix("$ cd "))
                }
            }
            line.first().isDigit() -> {
                val size = line.substringBefore(' ').toLong()
                val dirCopy = currentDir.toMutableList()
                while (true) {
                    val fullPath = dirCopy.toFullPath()
                    mapSize[fullPath] = size + (mapSize[fullPath] ?: 0L)
                    if (dirCopy.isEmpty()) {
                        break
                    }
                    dirCopy.removeLast()
                }
            }
        }
    }
    return mapSize
}
