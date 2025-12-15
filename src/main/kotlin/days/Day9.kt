package days

import kotlin.math.max
import kotlin.math.min

class Day9 : Day(9) {
    
    class Point2D(val x: Long, val y: Long) {
        override fun toString(): String {
            return "($x, $y)"
        }

        override fun equals(other: Any?): Boolean {
            return other is Point2D && other.x == x && other.y == y
        }

        override fun hashCode(): Int {
            var result = x.hashCode()
            result = 31 * result + y.hashCode()
            return result
        }
    }
    
    override fun partOne(): Any {
        
        val points = inputList.map {
            val coords = it.split(",").map { coordinate -> coordinate.toLong() }
            Point2D(coords[0], coords[1])
        }
        
        var maxArea = 0L
        for (i in points.indices) {
            for (j in i + 1 until points.size) {
                val point1 = points[i]
                val point2 = points[j]
                val area = calculateRectangleArea(point1, point2)
                if (area > maxArea) {
                    maxArea = area
                }
            }
        }
        
        return maxArea
    }

    override fun partTwo(): Any {
        
        val redPoints = inputList.map {
            val coords = it.split(",").map { coordinate -> coordinate.toLong() }
            Point2D(coords[0], coords[1])
        }
        
        val polygonEdges = createPolygonEdges(redPoints).flatten().toSet()
        
        //for every combination of two redPoints, check if the rectangle formed by them as diagonal is inside the polygon
        var maxArea = 0L
        for (i in redPoints.indices) {
            for (j in i + 1 until redPoints.size) {
                val point1 = redPoints[i]
                val point2 = redPoints[j]
                
                if (rectangleIsInsidePolygon(point1, point2, polygonEdges)) {
                    val area = calculateRectangleArea(point1, point2)
                    if (area > maxArea) {
                        maxArea = area
                    }
                }
            }
        }
        return maxArea
    }

    private fun createPolygonEdges(
        redPoints: List<Point2D>,
    ): MutableSet<List<Point2D>> {
        val polygonEdges = mutableSetOf<List<Point2D>>()
        redPoints.windowed(size = 2, step = 1) { pair ->
            val p1 = pair[0]
            val p2 = pair[1]
            //add all points between p1 and p2 to polygonEdges
            val edge = mutableListOf<Point2D>()
            
            if (p1.x == p2.x) {
                //horizontal line
                val range = if (p1.y < p2.y) p1.y..p2.y else p2.y..p1.y
                for (y in range) {
                    edge.add(Point2D(p1.x, y))
                }
            } else if (p1.y == p2.y) {
                //vertical line
                val range = if (p1.x < p2.x) p1.x..p2.x else p2.x..p1.x
                for (x in range) {
                    edge.add(Point2D(x, p1.y))
                }
            } else {
                throw IllegalArgumentException("Only horizontal and vertical lines are supported")
            }
            polygonEdges.add(edge)
        }
        //add edge between last and first point
        val firstPoint = redPoints.first()
        val lastPoint = redPoints.last()
        val closingEdge = mutableListOf<Point2D>()
        if (firstPoint.x == lastPoint.x) {
            //horizontal line
            val range = if (firstPoint.y < lastPoint.y) firstPoint.y..lastPoint.y else lastPoint.y..firstPoint.y
            for (y in range) {
                closingEdge.add(Point2D(firstPoint.x, y))
            }
        } else if (firstPoint.y == lastPoint.y) {
            //vertical line
            val range = if (firstPoint.x < lastPoint.x) firstPoint.x..lastPoint.x else lastPoint.x..firstPoint.x
            for (x in range) {
                closingEdge.add(Point2D(x, firstPoint.y))
            }
        } else {
            throw IllegalArgumentException("Only horizontal and vertical lines are supported")
        }
        polygonEdges.add(closingEdge)
        return polygonEdges
    }

    private fun rectangleIsInsidePolygon(
        point1: Point2D,
        point2: Point2D,
        polygonEdges: Set<Point2D>
    ): Boolean {
        //check edges of the rectangle
        var xMin = min(point1.x, point2.x) 
        var xMax = max(point1.x, point2.x) 
        var yMin = min(point1.y, point2.y) 
        var yMax = max(point1.y, point2.y)
        
        if (xMin != xMax) {
            xMin += 1
            xMax -= 1
        }
        if (yMin != yMax) {
            yMin += 1
            yMax -= 1
        }
        
        //create the edges of the rectangle
        //top edge
        val topEdge = mutableListOf<Point2D>()
        for (y in yMin..yMax) {
            topEdge.add(Point2D(xMin, y))
        }
        //bottom edge
        val bottomEdge = mutableListOf<Point2D>()
        for (y in yMin..yMax) {
            bottomEdge.add(Point2D(xMax, y))
        }
        //left edge
        val leftEdge = mutableListOf<Point2D>()
        for (x in xMin..xMax) {
            leftEdge.add(Point2D(x, yMin))
        }
        //right edge
        val rightEdge = mutableListOf<Point2D>()
        for (x in xMin..xMax) {
            rightEdge.add(Point2D(x, yMax))
        }
        
        val edges = listOf(topEdge, bottomEdge, leftEdge, rightEdge).flatten().toSet()
        
        //check if any of the edges points is in the polygon edges (that means they cross)
        for (edgePoint in edges) {
            if (polygonEdges.contains(edgePoint)) {
                return false
            }
        }
        return true
    }

    private fun calculateRectangleArea(point1: Point2D, point2: Point2D): Long {
        val length = kotlin.math.abs(point1.x - point2.x) + 1
        val width = kotlin.math.abs(point1.y - point2.y) + 1
        return length * width
    }
}