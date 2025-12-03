package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day3Test {

    private val dayThree = Day3()

    @Test
    fun testPartOne() {
        assertThat(dayThree.partOne(), `is`(357))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayThree.partTwo(), `is`(3121910778619))
    }
}
