import kotlin.math.absoluteValue

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()
    input.forEach { line ->
        val split = line.split("   ")
        a.add(split[0].toInt())
        b.add(split[1].toInt())
    }
    a.sort()
    b.sort()
    var sum = 0
    for (i in a.indices) {
        sum += (a[i] - b[i]).absoluteValue
    }
    return sum
}

private fun part2(input: List<String>): Int {
    val a = mutableListOf<Int>()
    val b = mutableListOf<Int>()
    input.forEach { line ->
        val split = line.split("   ")
        a.add(split[0].toInt())
        b.add(split[1].toInt())
    }
    val bFrequency = b.groupingBy { it }.eachCount()
    var sum = 0
    a.forEach {
        sum += it * (bFrequency[it] ?: 0)
    }
    return sum
}
