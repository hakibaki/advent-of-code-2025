package days

import util.InputReader
import util.InputReader.getInputAsColumns

class Day6 : Day(6) {
    override fun partOne(): Any {

        val columns = getInputAsColumns(6) //this is changing the input file by trimming spaces in the last line which causes problems
        var sum = 0L
        for (i in columns.indices) {
            val operation = columns[i].split(" ").last()
            val column = columns[i].split(" ").dropLast(1).map { it.toLong() }

            val result = when (operation) {
                "+" -> column.sum()
                "*" -> column.reduce { acc, n -> acc * n }
                else -> 0L
            }
            sum += result
        }
        return sum
    }

    override fun partTwo(): Any {
        //read input file as two-dimensional array of characters
        val inputArray = InputReader.getInputAs2DCharArray(6)
        //rotate the array 90 degrees counterclockwise
        val rotatedArray = rotateCounterClockwise(inputArray)
        
        var sum = 0L

        var problemNumbers = listOf<Long>().toMutableList()
        var problemOperation = ""
        for (i in rotatedArray.indices) {
            if(String(rotatedArray[i]).trim().isBlank()) {
                problemOperation = String(rotatedArray[i-1]).last().toString()
                val result = when (problemOperation) {
                    "+" -> problemNumbers.sum()
                    "*" -> problemNumbers.reduce { acc, n -> acc * n }
                    else -> 0L
                }
                sum += result
                problemNumbers.clear()
            } else {
                val number = String(rotatedArray[i].dropLast(1).toCharArray()).trim().toLong()
                problemNumbers.add(number)
            }
        }
        problemOperation = String(rotatedArray[rotatedArray.size-1]).last().toString()
        val result = when (problemOperation) {
            "+" -> problemNumbers.sum()
            "*" -> problemNumbers.reduce { acc, n -> acc * n }
            else -> 0L
        }
        sum += result

        return sum
    }

    fun rotateCounterClockwise(matrix: Array<CharArray>): Array<CharArray> {
        val rows = matrix.size
        val cols = matrix[0].size
        val rotated = Array(cols) { CharArray(rows) }
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                rotated[cols - j - 1][i] = matrix[i][j]
            }
        }
        return rotated
    }

}