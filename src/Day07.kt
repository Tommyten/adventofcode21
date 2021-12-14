import kotlin.math.abs

fun main() {
    fun calculateCrabFuelCosts(input: List<String>, fuelFunction: (current: Int, target: Int) -> Int): Int {
        val crabSubmarinePositions = input.single().split(',').map { it.toInt() }.sorted()
        val costMap = mutableMapOf<Int, Int>()

        for (i in crabSubmarinePositions.first()..crabSubmarinePositions.last()) {
            costMap[i] = 0
            for(crabSub in crabSubmarinePositions) {
                costMap[i] = costMap[i]!! + fuelFunction(crabSub, i)
            }
        }

        return costMap.values.minOrNull()!!
    }

    fun part1(input: List<String>): Int {
        return calculateCrabFuelCosts(
            input = input,
            fuelFunction = { current, target -> abs(current - target) }
        )
    }

    fun part2(input: List<String>): Int {
        return calculateCrabFuelCosts(
            input = input,
            fuelFunction = { current, target ->
                // easy enough, using the gaussian sum formula :)
                val diff = abs(current - target)
                (diff*diff+diff)/2
            }
        )
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}
