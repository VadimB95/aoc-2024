fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 161)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 48)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    return input.sumOf(::sumOfMutipliers)
}

private fun part2(input: List<String>): Int {
    val inputJoined = input.joinToString("")
    var linePatched = inputJoined
    while (true) {
        val matchResult = "don't\\(\\).*?do\\(\\)".toRegex().find(linePatched) ?: break
        linePatched = linePatched.removeRange(matchResult.range)
    }
    return sumOfMutipliers(linePatched)
}

private fun sumOfMutipliers(line: String): Int {
    val matchResults = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex().findAll(line)
    return matchResults.sumOf {
        val multipliers = it.groupValues[0].split(",").map { it.filter(Char::isDigit).toInt() }
        multipliers[0] * multipliers[1]
    }
}
