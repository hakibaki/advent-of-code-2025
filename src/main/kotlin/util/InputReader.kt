package util

import java.io.File

object InputReader {

    fun getInputAsString(day: Int): String {
        return fromResources(day).readText()
    }

    fun getInputAsList(day: Int): List<String> {
        return fromResources(day).readLines()
    }

    fun getInputAsColumns(day: Int): List<String> {
        //read the file into columns, columns are separated by one or more spaces
        val file = fromResources(day)
        val columns = mutableListOf<String>()
        file.forEachLine { line ->
            val values = line.trim().split(Regex("\\s+"))
            for (i in values.indices) {
                if (columns.size <= i) {
                    columns.add("")
                }
                columns[i] += if (columns[i].isEmpty()) values[i] else " " + values[i]
            }
        }
        return columns
    }

    private fun fromResources(day: Int): File {
        return File(javaClass.classLoader.getResource("input_day_$day.txt").toURI())
    }

    fun getInputAs2DCharArray(day: Int): Array<CharArray> {
        val lines = getInputAsList(day)
        return Array(lines.size) { i -> lines[i].toCharArray() }
    }
}
