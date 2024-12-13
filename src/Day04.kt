fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

private fun part1(input: List<String>): Int {
    var counter = 0
    val word = "XMAS"
    for (i in input.indices) for (j in input[i].indices) {
        if (input[i][j] != word[0]) continue
        val directionsCoincidences = Array(8) { 1 }
        for (k in 1..word.lastIndex) {
            if (input.getOrNull(i - k)?.getOrNull(j) == word[k]) directionsCoincidences[0]++
            if (input.getOrNull(i - k)?.getOrNull(j + k) == word[k]) directionsCoincidences[1]++
            if (input.getOrNull(i)?.getOrNull(j + k) == word[k]) directionsCoincidences[2]++
            if (input.getOrNull(i + k)?.getOrNull(j + k) == word[k]) directionsCoincidences[3]++
            if (input.getOrNull(i + k)?.getOrNull(j) == word[k]) directionsCoincidences[4]++
            if (input.getOrNull(i + k)?.getOrNull(j - k) == word[k]) directionsCoincidences[5]++
            if (input.getOrNull(i)?.getOrNull(j - k) == word[k]) directionsCoincidences[6]++
            if (input.getOrNull(i - k)?.getOrNull(j - k) == word[k]) directionsCoincidences[7]++
        }
        counter += directionsCoincidences.count { it == word.length }
    }
    return counter
}

private fun part2(input: List<String>): Int {
    var counter = 0
    for (i in input.indices) for (j in input[i].indices) {
        val lt = input.getOrNull(i - 1)?.getOrNull(j - 1)
        val rt = input.getOrNull(i - 1)?.getOrNull(j + 1)
        val rb = input.getOrNull(i + 1)?.getOrNull(j + 1)
        val lb = input.getOrNull(i + 1)?.getOrNull(j - 1)
        val allowedChars = setOf('M', 'S')
        if (lt !in allowedChars || rt !in allowedChars || rb !in allowedChars || lb !in allowedChars) continue
        if (input[i][j] == 'A' && lt != rb && rt != lb) counter++
    }
    return counter
}
