package days

class Day5 : Day(5) {

    override fun partOne(): Any {
        //input file has two parts separated by a blank line
        //first part contains range of numbers in format "start-end", each on a new line
        //second part contains a list of numbers, one per line
        val parts = inputString.split("\n\n")
        val rangeLines = parts[0].lines().filter { it.isNotBlank() }
        val numberLines = parts[1].lines().filter { it.isNotBlank() }
        val ranges = rangeLines.map {
            val rangeParts = it.split('-')
            val start = rangeParts[0].toLong()
            val end = rangeParts[1].toLong()
            start..end
        }
        var count = 0L
        numberLines.forEach {
            val number = it.toLong()
            if (ranges.any { range -> number in range }) {
                count++
            }
        }
        return count
    }

    override fun partTwo(): Any {
        val parts = inputString.split("\n\n")
        val rangeLines = parts[0].lines().filter { it.isNotBlank() }
        var count = 0L

        val ranges = rangeLines.map {
            val rangeParts = it.split('-')
            val start = rangeParts[0].toLong()
            val end = rangeParts[1].toLong()
            start..end
        }.sortedBy { it.first }.toMutableList()

        for (i in 0 until ranges.size - 1) {
            val currentRange = ranges[i]
            val nextRange = ranges[i + 1]
            if (currentRange.last >= nextRange.first) {
                val mergedRange = currentRange.first..maxOf(currentRange.last, nextRange.last)
                ranges[i + 1] = mergedRange
            } else {
                count += currentRange.last - currentRange.first + 1
            }
        }
        // add the last range
        val lastRange = ranges.last()
        count += lastRange.last - lastRange.first + 1

        return count
    }
}