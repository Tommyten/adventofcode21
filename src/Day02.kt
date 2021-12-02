fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontal = 0

        val instructionRegex = """(\w+)\s(\d+)""".toRegex()

        for(line in input) {
            val (instruction, count) = instructionRegex.find(line)!!.destructured
            when(instruction) {
                "forward" -> horizontal += count.toInt()
                "down" -> depth += count.toInt()
                "up" -> depth -= count.toInt()
                else -> throw IllegalArgumentException("Unknown Instruction")
            }
        }

        // jetbrains if you're looking at this: I get a "value is always zero" warning here
        return depth * horizontal
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var depth = 0
        var horizontal = 0

        val instructionRegex = """(\w+)\s(\d+)""".toRegex()

        for(line in input) {
            val (instruction, count) = instructionRegex.find(line)!!.destructured
            when(instruction) {
                "forward" -> {
                    horizontal += count.toInt()
                    // jetbrains if you're looking at this: here again :)
                    depth += aim * count.toInt()
                }
                "down" -> aim += count.toInt()
                "up" -> aim -= count.toInt()
                else -> throw IllegalArgumentException("Unknown Instruction")
            }
        }

        // jetbrains if you're looking at this: I get a "value is always zero" warning here
        return depth * horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

