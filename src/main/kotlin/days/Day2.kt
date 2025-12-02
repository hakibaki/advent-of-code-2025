package days

class Day2 : Day(2) {

    override fun partOne(): Any {
        // for each range spawn sequence of numbers in the range
        // if the number has odd number of digits, skip
        // if the number has even number of digits split it in half
        // if those halves are equal, count the whole number
        var count = 0L;
        inputString.trim().split(",").forEach {
            val rangeParts = it.split('-')
            val start = rangeParts[0].toLong()
            val end = rangeParts[1].toLong()
            for (num in start..end) {
                val numStr = num.toString()
                if (numStr.length % 2 != 0) continue;
                val halfLength = numStr.length / 2
                val firstHalf = numStr.substring(0, halfLength)
                val secondHalf = numStr.substring(halfLength)
                if (firstHalf == secondHalf) {
                    count += num
                }
            }
        }
        return count
    }

    override fun partTwo(): Any {
        var count = 0L;
        inputString.trim().split(",").forEach {
            val rangeParts = it.split('-')
            val start = rangeParts[0].toLong()
            val end = rangeParts[1].toLong()
            for (num in start..end) {
                if (isNumberInvalid(num)) count += num
            }
        }
        return count
    }

    fun isNumberInvalid(num: Long): Boolean {
        //split the number into chunks of 1, 2, 3, ... up to half the length of the number
        //check if all the chunks are the same
        val numStr = num.toString()
        val length = numStr.length
        for (chunkSize in 1..(length / 2)) {
            if (length % chunkSize != 0) continue;
            numStr.chunked(chunkSize).let { chunks ->
                val firstChunk = chunks[0]
                if (chunks.all { it == firstChunk }) {
                    return true
                }
            }
        }
        return false
    }
}