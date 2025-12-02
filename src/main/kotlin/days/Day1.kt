package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        var position = 50;
        var countsOfZeroes = 0;
        inputList.forEach {
            val rotation = it.substring(1).toInt()
            val direction = if (it[0] == 'R')  1 else -1;
            position += rotation * direction
            position %= 100
            if (position == 0) { countsOfZeroes++ }
        }
        return countsOfZeroes;
    }

    override fun partTwo(): Any {
        var position = 50;
        var clicks = 0;
        inputList.forEach {
            //print("Rotation: $it , ")
            var rotation = it.substring(1).toInt()
            if (it.startsWith("L")) {
                rotation = -rotation;
            }
            clicks += countClicks(position, rotation)
            position += rotation;
            position %= 100
            if (position < 0) {
                position += 100;
            }
            //println("Position: $position , clicks = $countsOfZeroes")
        }
        return clicks;
    }

    fun countClicks(startPosition: Int, rotation: Int): Int {
        var clicks = 0;
        var endPosition = startPosition + rotation;
        if (endPosition < 0) {
            if (startPosition == 0) clicks--
            while (endPosition < 0) {
                endPosition += 100
                clicks++
            }
        }
        while (endPosition >= 100) {
            endPosition -= 100
            if (endPosition != 0) clicks++
        }
        if (endPosition == 0) clicks++
        return clicks;
    }
}
