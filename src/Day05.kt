import kotlin.math.max

val inputRegex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

fun main() {
    fun part1(input: List<String>, considerDiagonals: Boolean = false): Int {
        val lineCoordinates: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableListOf()

        for (line in input) {
            val (x1, y1, x2, y2) = inputRegex.find(line)!!.destructured
            if (x1 != x2 && y1 != y2 && !considerDiagonals) {
                continue
            }
            lineCoordinates.add((x1.toInt() to y1.toInt()) to (x2.toInt() to y2.toInt()))
        }
        val maxCoordinate = lineCoordinates.maxOf {
            max(it.first.maxComponent(), it.second.maxComponent())
        }

        val seaFloor = SeaFloor(maxCoordinate)
        for (lineCoordinate in lineCoordinates) {
            seaFloor.addLineOfVents(lineCoordinate.first, lineCoordinate.second)
        }
        return seaFloor.getNumOfMoreThanOneVent()
    }

    fun part2(input: List<String>): Int {
        return part1(input, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println("Output part one: ${part1(input)}")
    println("Output part two: ${part2(input)}")
}

fun Pair<Int, Int>.maxComponent(): Int = max(first, second)

class SeaFloor(maxNumber: Int) {

    val ventCountMatrix = MutableList(maxNumber + 1) { MutableList(maxNumber + 1) { 0 } }

    fun addLineOfVents(startCoordinate: Pair<Int, Int>, endCoordinate: Pair<Int, Int>) {
        val (x1, y1) = startCoordinate
        val (x2, y2) = endCoordinate

        val xRange = if (x1 > x2) {
            x1 downTo x2
        } else {
            x1..x2
        }.iterator()

        val yRange = if (y1 > y2) {
            y1 downTo y2
        } else {
            y1..y2
        }.iterator()

        var currentX = xRange.next()
        var currentY = yRange.next()
        ventCountMatrix[currentY][currentX] += 1

        /*for (x in xRange) {
            for (y in yRange) {
                ventCountMatrix[y][x] += 1
            }
        }*/
        var goOn = true
        while(goOn) {
            if(xRange.hasNext()) currentX = xRange.next()
            if(yRange.hasNext()) currentY = yRange.next()

            ventCountMatrix[currentY][currentX] += 1

            if(!xRange.hasNext() && !yRange.hasNext()) {
                goOn = false
            }
        }
    }

    fun getNumOfMoreThanOneVent(): Int {
        var total = 0
        for (line in ventCountMatrix) {
            total += line.filter { it >= 2 }.fold(0) { acc, _ -> acc + 1 }
        }
        return total
    }

    override fun toString(): String {
        var output = ""
        for (line in ventCountMatrix) {
            for (i in line) {
                output += "${if(i==0)'.' else i} "
            }
            output += "\n"
        }
        return output
    }
}
