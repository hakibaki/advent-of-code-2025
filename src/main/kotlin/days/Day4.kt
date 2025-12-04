package days

class Day4 : Day(4) {
    override fun partOne(): Any {
        //load input as two-dimensional array of characters, remove empty lines
        //for each row nad for each column check if there is a '@' character
        //if yes, then check all adjacent cells (including diagonals) for '@' characters
        //if there are fewer than 4 adjacent '@' characters, count this '@' character
        val grid = inputList.map { it.toCharArray() }
        return countRemovableRolls(grid).count();
    }

    override fun partTwo(): Any {
        var count = 0;
        val grid = inputList.map { it.toCharArray() }
        do {
            val removableRolls = countRemovableRolls(grid)
            count += removableRolls.count()
            removableRolls.forEach { (i, j) ->
                grid[i][j] = '.'
            }
            val removableCount = removableRolls.count()
        } while (removableCount > 0)
        return count;
    }
}

private fun countRemovableRolls(grid: List<CharArray>): List<Pair<Int, Int>> {
    var removableRollsPositions = mutableListOf<Pair<Int, Int>>();
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == '@') {
                var adjacentCount = 0;
                for (di in -1..1) {
                    for (dj in -1..1) {
                        if (di == 0 && dj == 0) continue;
                        val ni = i + di;
                        val nj = j + dj;
                        if (ni in grid.indices && nj in grid[ni].indices && grid[ni][nj] == '@') {
                            adjacentCount++;
                        }
                    }
                }
                if (adjacentCount < 4) {
                    removableRollsPositions.add(Pair(i, j));
                }
            }
        }
    }
    return removableRollsPositions
}