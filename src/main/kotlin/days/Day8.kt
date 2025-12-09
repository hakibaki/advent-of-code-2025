package days

class Day8 : Day(8) {
    
    data class Point3D(val x: Long, val y: Long, val z: Long) {
        override fun toString(): String {
            return "($x, $y, $z)"
        }
    }
    
    override fun partOne(): Any {
        val points = loadPointsFromInput()
        val distances = calculateDistancesSorted(points)
        val listOfSets = mutableListOf<Set<Point3D>>()
        points.forEach {listOfSets.add(setOf(it))}
        
        val n = 1000 //for test to pass, change to 10
        
        distances.entries.take(n).forEach { (pointPair, _) ->
            val point1 = pointPair.first
            val point2 = pointPair.second
            
            val setContainingPoint1 = listOfSets.find { it.contains(point1) }
            val setContainingPoint2 = listOfSets.find { it.contains(point2) }
            
            if (setContainingPoint1 != null && setContainingPoint2 != null && setContainingPoint1 != setContainingPoint2) {
                //merge sets
                val mergedSet = setContainingPoint1.union(setContainingPoint2)
                listOfSets.remove(setContainingPoint1)
                listOfSets.remove(setContainingPoint2)
                listOfSets.add(mergedSet)
            }
        }
        
        listOfSets.sortByDescending { it.size }
        if (listOfSets.sumOf { it.size } != points.size) {
            throw IllegalStateException("Some points are missing from sets or are duplicated")
        }
        
        return listOfSets[0].size * listOfSets[1].size * listOfSets[2].size
    }

    override fun partTwo(): Any {
        val points = loadPointsFromInput()
        val distances = calculateDistancesSorted(points)
        val listOfSets = mutableListOf<Set<Point3D>>()
        points.forEach {listOfSets.add(setOf(it))}

        distances.entries.forEach {
            val point1 = it.key.first
            val point2 = it.key.second

            val setContainingPoint1 = listOfSets.find { it.contains(point1) }
            val setContainingPoint2 = listOfSets.find { it.contains(point2) }

            if (setContainingPoint1 != null && setContainingPoint2 != null && setContainingPoint1 != setContainingPoint2) {
                //merge sets
                val mergedSet = setContainingPoint1.union(setContainingPoint2)
                listOfSets.remove(setContainingPoint1)
                listOfSets.remove(setContainingPoint2)
                listOfSets.add(mergedSet)
            }
            if (listOfSets.size == 1) {
                return point1.x * point2.x
            }
        }
        throw IllegalStateException("Could not reduce to single set")
    }

    private fun loadPointsFromInput(): MutableList<Point3D> {
        val points = inputList.map {
            val coords = it.split(",").map { coordinate -> coordinate.toLong() }
            Point3D(coords[0], coords[1], coords[2])
        }.toMutableList()
        return points
    }

    private fun calculateDistancesSorted(points: MutableList<Point3D>): MutableMap<Pair<Point3D, Point3D>, Long> {
        //calculate distance between every combination of points
        var distances = mutableMapOf<Pair<Point3D, Point3D>, Long>()
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val dist = squaredDistance(points[i], points[j])
                //println("Distance between ${points[i]} and ${points[j]} is $dist")
                distances[Pair(points[i], points[j])] = dist
            }
        }
        println("distances size: ${distances.values.size}")
        distances = distances.toList()
            .sortedBy { (key, value) -> value }
            .toMap().toMutableMap()
        return distances
    }

    /**
     * Calculates the squared Euclidean distance between two 3D points.
     */
    fun squaredDistance(p1: Point3D, p2: Point3D): Long {
        val dx = p1.x - p2.x
        val dy = p1.y - p2.y
        val dz = p1.z - p2.z
        return dx * dx + dy * dy + dz * dz // Faster than using sqrt()
    }
}

