package days

class Day7 : Day(7) {
    
    override fun partOne(): Any {
        
        val beamIndexes = setOf<Int>().toMutableSet()
        
        var numberOfSplits = 0
        inputList.forEachIndexed { lineIndex, line ->
            if (line.contains('S')) {
                beamIndexes.add(line.indexOf('S'))
            } else {
                line.forEachIndexed { charIndex, c ->
                    if (c == '^' && beamIndexes.contains(charIndex)) {
                        beamIndexes.remove(charIndex)
                        beamIndexes.add(charIndex - 1)
                        beamIndexes.add(charIndex + 1)
                        numberOfSplits++
                    }
                }
            }
        }
        
        
        return numberOfSplits
    }
    override fun partTwo(): Any {
        val beamValues = LongArray(inputList.first().length)

        inputList.forEach { line ->
            if (line.contains('S')) {
                beamValues[line.indexOf('S')] = 1L
            } else {
                line.forEachIndexed { charIndex, c ->
                    if (c == '^' && beamValues[charIndex] > 0) {
                        beamValues[charIndex - 1] += beamValues[charIndex]
                        beamValues[charIndex + 1] += beamValues[charIndex]
                        beamValues[charIndex] = 0
                    }
                }
            }
        }
        return beamValues.sum()
    }
}