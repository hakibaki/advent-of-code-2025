package days


class Day3 : Day(3)
{
    override fun partOne(): Any {
        var sum = 0;
        inputList.forEach {
            if (it.isNotEmpty()) {
                sum += calculateMaxJoltage(it, 2).toInt()
            }
        }
        return sum
    }

   private fun findMaxNumberInString(sequenceOfNumbers: String): Int {
        var maxNumber = Int.MIN_VALUE
        for (char in sequenceOfNumbers) {
            val num = char.toString().toInt()
            if (num > maxNumber) {
                maxNumber = num
            }
        }
        return maxNumber
    }

    override fun partTwo(): Any {
        var sum = 0L;
        inputList.forEach {
            if (it.isNotEmpty()) {
                sum += calculateMaxJoltage(it, 12).toLong()
            }
        }
        return sum
    }

    private fun calculateMaxJoltage(batteriesPack: String, numberOfBatteriesToTake: Int): String {
        if (numberOfBatteriesToTake < 1) {
            return "";
        }
        var joltageNumber = ""
        joltageNumber += findMaxNumberInString(batteriesPack.dropLast(numberOfBatteriesToTake - 1)).toString()
        val remainingPack = batteriesPack.substringAfter(joltageNumber)
        joltageNumber += calculateMaxJoltage(remainingPack, numberOfBatteriesToTake - 1)
        //println("joltageNumber: $joltageNumber")
        return joltageNumber
    }
}