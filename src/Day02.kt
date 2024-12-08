import kotlin.math.abs

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val reports = input.map { it.split(" ").map(String::toInt) }
    return reports.count { report -> report.isSafe() }
}

private fun part2(input: List<String>): Int {
    val reports = input.map { it.split(" ").map(String::toInt) }
    return reports.count { report ->
        if (report.isSafe()) {
            true
        } else {
            var isSafeTolerated = false
            for (i in report.indices) {
                if (report.toMutableList().apply { removeAt(i) }.isSafe()) {
                    isSafeTolerated = true
                    break
                }
            }
            isSafeTolerated
        }
    }
}

private fun List<Int>.isSafe(): Boolean {
    val isIncreasing = this[1] > this[0]
    return windowed(2).all {
        (if (isIncreasing) it[1] > it[0] else it[1] < it[0]) && abs(it[0] - it[1]) in 1..3
    }
}
