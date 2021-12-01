fun main() {
    fun part1(input: List<String>): Int {
        var numOfIncreases = 0

        var previousNumber = Integer.MAX_VALUE
        for(line in input) {
            val parsedLine = line.toInt()
            if(previousNumber < parsedLine) {
               numOfIncreases++
            }
            previousNumber = parsedLine
        }

        return numOfIncreases
    }

    fun part2(input: List<String>): Int {

        var numOfIncreases = 0

        var previousSum = Integer.MAX_VALUE
        for(i in input.indices) {
            if(i+2 >= input.size) {
                break
            }

            val first = input[i].toInt()
            val second = input[i+1].toInt()
            val third = input[i+2].toInt()
            val sum = first+second+third

            if(sum > previousSum) {
                numOfIncreases++
            }
            previousSum = sum
        }

        return numOfIncreases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}
