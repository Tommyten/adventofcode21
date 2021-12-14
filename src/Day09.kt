fun main() {
    fun part1(input: List<String>): Int {
        val lowPoints = mutableListOf<Int>()
        for(y in input.indices) {
            for(x in input[y].indices) {
                if(input.isLowPoint(x, y)) {
                    lowPoints.add(input[y][x].digitToInt())
                }
            }
        }

        return lowPoints.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {
        val map = input.map { it.toCharArray().map { char -> char.digitToInt() to false }.toMutableList() }.toMutableList()
        val basinSizes = mutableListOf<Int>()

        fun floodFill(x: Int, y: Int) {
            if(y >= 0 && y < map.size && x >= 0 && x < map.first().size) {
                if (!map[y][x].second && map[y][x].first != 9) {
                    map[y][x] = map[y][x].first to true
                    basinSizes[basinSizes.lastIndex] += 1
                    floodFill(x + 1, y)
                    floodFill(x - 1, y)
                    floodFill(x, y + 1)
                    floodFill(x, y - 1)
                }
            }
        }

        for(y in map.indices) {
            for(x in map[y].indices) {
                if(!map[y][x].second && map[y][x].first != 9) {
                    basinSizes.add(0)
                    floodFill(x, y)
                }
            }
        }

        return basinSizes.sorted().reversed().take(3).reduce { acc, i -> acc * i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

fun List<String>.isLowPoint(x: Int, y: Int): Boolean {
    val currentValue = this[y][x].digitToInt()
    val neighbors = mutableListOf<Int>()
    try {
        neighbors.add(this[y - 1][x].digitToInt())
    } catch (e: Exception) {
    }
    try {
        neighbors.add(this[y + 1][x].digitToInt())
    } catch (e: Exception) {
    }
    try {
        neighbors.add(this[y][x - 1].digitToInt())
    } catch (e: Exception) {
    }
    try {
        neighbors.add(this[y][x + 1].digitToInt())
    } catch (e: Exception) {
    }
    return neighbors.all { it > currentValue }
}