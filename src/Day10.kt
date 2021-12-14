import java.lang.RuntimeException

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        navigationSubsystem@ for (navigationSubsystem in input) {
            val currentStack = mutableListOf<Char>()
            for (character in navigationSubsystem.toCharArray()) {
                if (character.isOpening()) {
                    currentStack.add(character)
                } else if(character.isClosing()) {
                    if(character.isMatchingBracket(currentStack.last())) {
                        currentStack.removeLast()
                    } else {
                        sum += when(character) {
                            ')' -> 3
                            ']' -> 57
                            '}' -> 1197
                            '>' -> 25137
                            else -> 0
                        }
                        continue@navigationSubsystem
                    }
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Long {
        val completionScores = mutableListOf<Long>()
        navigationSubsystem@ for (navigationSubsystem in input) {
            val currentStack = mutableListOf<Char>()
            for (character in navigationSubsystem.toCharArray()) {
                if (character.isOpening()) {
                    currentStack.add(character)
                } else if(character.isClosing()) {
                    if(character.isMatchingBracket(currentStack.last())) {
                        currentStack.removeLast()
                    } else {
                        continue@navigationSubsystem
                    }
                }
            }
            var intermediateSum = 0L
            for(character in currentStack.reversed()) {
                intermediateSum *= 5
                intermediateSum += when(character.getMatchingBracket()) {
                    ')' -> 1
                    ']' -> 2
                    '}' -> 3
                    '>' -> 4
                    else -> throw RuntimeException("Impossible input")
                }
            }
            completionScores.add(intermediateSum)
        }

        return completionScores.sorted()[(completionScores.size-1)/2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

fun Char.isOpening(): Boolean = equals('(') || equals('{') || equals('[') || equals('<')
fun Char.isClosing(): Boolean = equals(')') || equals('}') || equals(']') || equals('>')
fun Char.isMatchingBracket(other: Char): Boolean =
    (equals(')') && other == '(') ||
            (equals('}') && other == '{') ||
            (equals(']') && other == '[') ||
            (equals('>') && other == '<')
fun Char.getMatchingBracket(): Char =
    when(this) {
        '(' -> ')'
        '{' -> '}'
        '[' -> ']'
        '<' -> '>'
        else -> throw RuntimeException("Impossible input")
    }
