fun main() {
    fun part1(input: List<String>): Int {
        val inputIterator = input.iterator()
        val drawnNumbers = inputIterator.next().split(",").map { it.toInt() }
        inputIterator.next()

        val boards: MutableList<Board> = mutableListOf()
        var currentBoard = Board()

        for (line in inputIterator) {
            currentBoard.addLine(line)
            if (line.isBlank() || !inputIterator.hasNext()) {
                boards.add(currentBoard)
                currentBoard = Board()
                continue
            }
        }

        var winnerBoard = Board()
        var lastDrawnNumber = 0
        outerFor@ for (number in drawnNumbers) {
            for (board in boards) {
                board.drawNumber(number)
                if (board.hasWin()) {
                    winnerBoard = board
                    lastDrawnNumber = number
                    break@outerFor
                }
            }
        }
        val sum = winnerBoard.sumOfUnmarked()

        return sum * lastDrawnNumber
    }

    fun part2(input: List<String>): Int {
        val inputIterator = input.iterator()
        val drawnNumbers = inputIterator.next().split(",").map { it.toInt() }
        inputIterator.next()

        val boards: MutableList<Board> = mutableListOf()
        var currentBoard = Board()

        for (line in inputIterator) {
            currentBoard.addLine(line)
            if (line.isBlank() || !inputIterator.hasNext()) {
                boards.add(currentBoard)
                currentBoard = Board()
                continue
            }
        }

        val winnerBoards = mutableListOf<Board>()
        var winnerBoard = Board()
        var lastDrawnNumber = 0
        outerFor@ for (number in drawnNumbers) {
            for (board in boards) {
                board.drawNumber(number)
                if (board.hasWin() && !winnerBoards.contains(board)) {
                    winnerBoard = board
                    lastDrawnNumber = number
                    winnerBoards.add(board)
                }
                if(winnerBoards.size == boards.size) {
                    break@outerFor
                }
            }
        }
        val sum = winnerBoard.sumOfUnmarked()

        return sum * lastDrawnNumber
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

class Board {

    val matrix: MutableList<MutableList<Pair<Int, Boolean>>> = MutableList(5) { mutableListOf() }
    val matrixRotated: MutableList<MutableList<Pair<Int, Boolean>>> = mutableListOf()

    fun addLine(line: String) {
        if(line.isBlank()) {
            return
        }
        val entries = line.trim().split("""\s+""".toRegex()).map { it.toInt() to false }
        for (i in 0 until 5) {
            matrix[i].add(entries[i])
        }
        matrixRotated.add(entries.toMutableList())
    }

    fun drawNumber(number: Int) {
        for (line in matrix) {
            val item = line.find { it.first == number && !it.second }
            if (item != null) {
                val index = line.indexOf(item)
                line[index] = number to true
            }
        }
        for (line in matrixRotated) {
            val item = line.find { it.first == number && !it.second }
            if (item != null) {
                val index = line.indexOf(item)
                line[index] = number to true
            }
        }
    }

    fun hasWin(): Boolean {
        for (line in matrix) {
            if (line.all { it.second }) {
                return true
            }
        }
        for (line in matrixRotated) {
            if (line.all { it.second }) {
                return true
            }
        }
        return false
    }

    fun sumOfUnmarked(): Int {
        var sum = 0
        for(line in matrix) {
            sum += line.filter { !it.second }.map { it.first }.fold(0) { acc, i -> acc+i }
        }
        return sum
    }
}
