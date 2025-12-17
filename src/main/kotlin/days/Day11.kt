package days

import org.apache.commons.math3.linear.MatrixUtils

class Day11 : Day(11) {
    
    override fun partOne(): Any {
        
        val machineNames = mutableSetOf<String>()
        val transitions = mutableListOf<List<String>>()
        inputList.forEach { 
            machineNames.add(it.split(":")[0])
            transitions.add(it.split(":")[1].trim().split(" "))
        }
        machineNames.add("out")
        
        //create transition matrix
        val transitionMatrix = Array(machineNames.size) { IntArray(machineNames.size) { 0 } }
        transitions.forEachIndexed { i, transitionList ->
            transitionList.forEach { transition ->
                val index = machineNames.indexOf(transition)
                transitionMatrix[i][index] = 1
            }
        }
        
        var inputVector = IntArray(machineNames.size) { 0 }
        inputVector[machineNames.indexOf("you")] = 1
        
        // Convert to RealMatrix for matrix operations
        val realMatrix = MatrixUtils.createRealMatrix(
            transitionMatrix.map { it.map { it.toDouble() }.toDoubleArray() }.toTypedArray()
        )
        //println(realMatrix)
        
        var possiblePathsCount = 0L
        while (inputVector.any { it != 0 }) {
            //multiply vector and matrix
            val vectorArray = DoubleArray(inputVector.size) { inputVector[it].toDouble() }
            val resultArray = realMatrix.transpose().operate(vectorArray)
            inputVector = resultArray.map { it.toInt() }.toIntArray()
            possiblePathsCount += inputVector.last()
        }
        
        return possiblePathsCount
    }
    
    override fun partTwo(): Any {
        val machineNames = mutableSetOf<String>()
        val transitions = mutableListOf<List<String>>()
        inputList.forEach {
            machineNames.add(it.split(":")[0])
            transitions.add(it.split(":")[1].trim().split(" "))
        }
        machineNames.add("out")

        //create transition matrix
        val transitionMatrix = Array(machineNames.size) { IntArray(machineNames.size) { 0 } }
        transitions.forEachIndexed { i, transitionList ->
            transitionList.forEach { transition ->
                val index = machineNames.indexOf(transition)
                transitionMatrix[i][index] = 1
            }
        }
        // Convert to RealMatrix for matrix operations
        val realMatrix = MatrixUtils.createRealMatrix(
            transitionMatrix.map { it.map { it.toDouble() }.toDoubleArray() }.toTypedArray()
        )
        
        val path = listOf("svr", "fft", "dac", "out")
        var possiblePathsCount = 1L
        path.windowed(2).forEach { pair ->
            var inputVector = IntArray(machineNames.size) { 0 }
            inputVector[machineNames.indexOf(pair[0])] = 1
            var pathsFromFirstToSecond = 0
            val indexOfSecondMachine = machineNames.indexOf(pair[1])
            while (inputVector.any { it != 0 }) {
                //multiply vector and matrix
                val vectorArray = DoubleArray(inputVector.size) { inputVector[it].toDouble() }
                val resultArray = realMatrix.transpose().operate(vectorArray)
                inputVector = resultArray.map { it.toInt() }.toIntArray()
                pathsFromFirstToSecond += inputVector[indexOfSecondMachine]
            }
            possiblePathsCount *= pathsFromFirstToSecond
        }
        
        return possiblePathsCount
    }
}