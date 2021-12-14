fun main() {
    // Credit to Sebastian Aigner (https://github.com/SebastianAigner/advent-of-code-2021/blob/master/src/main/kotlin/Day06.kt)
    fun generateFish(input: List<String>, numOfGenerations: Int): Long {
        var fishMap = input.single()
            .split(",")
            .map { it.toInt() }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, v) -> v.toLong() }

        repeat (numOfGenerations) {
            val newMap = fishMap.mapKeys { (key, _) -> key-1 }.toMutableMap()
            newMap[8] = newMap.getOrDefault(-1, 0)
            newMap[6] = newMap.getOrDefault(6, 0) + newMap.getOrDefault(-1, 0)
            newMap.remove(-1)
            fishMap = newMap
        }

        return fishMap.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(generateFish(testInput, 80) == 5934L)
    check(generateFish(testInput, 256) == 26984457539L)

    val input = readInput("Day06")
    println("Output part one: ${generateFish(input, 80)}")
    println("Output part two: ${generateFish(input, 256)}")
}
