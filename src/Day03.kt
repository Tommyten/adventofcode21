fun main() {
    fun part1(input: List<String>): Int {

        val gammaRateString = input.map {
            it.toList().map { character ->
                when (character) {
                    '0' -> 1 to 0
                    '1' -> 0 to 1
                    else -> throw IllegalStateException("Unexpected Input")
                }
            }
        }.reduce { acc, pairs ->
            return@reduce acc.mapIndexed { index, pair ->
                pair.add(pairs[index])
            }
        }.map {
            it.toMostCommonBit()
        }.joinToString(separator = "")

        // invert gammaRate to get epsilon Rate
        val epsilonRateString = gammaRateString.toList().map { character ->
            when (character) {
                '0' -> 1
                '1' -> 0
                else -> throw IllegalStateException("Unexpected Input")
            }
        }.joinToString(separator = "")

        val gammaRateInt = gammaRateString.toInt(2)
        val epsilonRateInt = epsilonRateString.toInt(2)

        return gammaRateInt * epsilonRateInt
    }

    fun part2(input: List<String>): Int {

        var remainingNumbers = input
        for(i in input[0].indices){
            if(remainingNumbers.size == 1) break

            val countOfZeros = remainingNumbers.count { it[i] == '0' }
            val countOfOnes = remainingNumbers.count { it[i] == '1' }
            remainingNumbers = if(countOfOnes >= countOfZeros) {
                remainingNumbers.filter { it[i] == '1' }
            } else {
                remainingNumbers.filter { it[i] == '0' }
            }
        }
        val oxygenGeneratorRating = remainingNumbers.single().toInt(2)

        remainingNumbers = input
        for(i in input[0].indices){
            if(remainingNumbers.size == 1) break

            val countOfZeros = remainingNumbers.count { it[i] == '0' }
            val countOfOnes = remainingNumbers.count { it[i] == '1' }
            remainingNumbers = if(countOfOnes < countOfZeros) {
                remainingNumbers.filter { it[i] == '1' }
            } else {
                remainingNumbers.filter { it[i] == '0' }
            }
        }
        val co2ScrubberRating = remainingNumbers.single().toInt(2)

        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

fun Pair<Int, Int>.add(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (first + pair.first) to (second + pair.second)
}

fun Pair<Int, Int>.toMostCommonBit(): Int =
    if (first > second) {
        0
    } else {
        1
    }
