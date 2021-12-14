import java.lang.RuntimeException

fun main() {
    fun part1(input: List<String>): Int {
        val inputs = input.map { it.split("|").map { it.trim().split(" ") } }

        var count = 0
        for(line in inputs) {
            val relevantValues = line.last()
            for(value in relevantValues) {
                if(value.length == 2 || value.length == 3 || value.length == 4 || value.length == 7) {
                    count++
                }
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val inputs = input.map { it.split("|").map { it.trim().split(" ") } }

        var count = 0
        for(line in inputs) {
            val relevantValues = line.last()
            var digit = ""
            for(value in relevantValues) {
                var temp = value.toCharArray().sorted().joinToString(separator = "")
                if(value.length == 2 || value.length == 3 || value.length == 4 || value.length == 7) {
                    digit += value.length
                    continue
                }
                digit += when(temp) {
                    "abcdefg" -> 8
                    "bcdef" -> 5
                    "acdfg" -> 2
                    "abcdf" -> 3
                    "abd" -> 7
                    "abcdef" -> 9
                    "bcdefg" -> 6
                    "abef" -> 4
                    "abcdeg" -> 0
                    "ab" -> 1
                    else -> throw RuntimeException("Impossible input")
                }
            }
            count += digit.toInt()
        }

        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

class Digit {

    var topChar: Char = '0'
    var midChar: Char = '0'
    var bottomChar: Char = '0'
    var topRightChar: Char = '0'
    var bottomRightChar: Char = '0'
    var topLeftChar: Char = '0'
    var bottomLeftChar: Char = '0'

    fun initDigits(one: String, four: String, ) {

    }

}
