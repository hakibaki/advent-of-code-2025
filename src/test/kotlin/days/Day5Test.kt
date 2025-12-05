package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day5Test {

    private val dayFive = Day5()

    @Test
    fun partOne() {
        assertThat(dayFive.partOne(), `is`(3L))
    }

    @Test
    fun partTwo() {
        assertThat(dayFive.partTwo(), `is`(14L))
    }

}