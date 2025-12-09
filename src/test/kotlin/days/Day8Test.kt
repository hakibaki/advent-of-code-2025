package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day8Test {

    private val dayEight = Day8()

    @Test
    fun partOne() {
        assertThat(dayEight.partOne(), `is`(40))
    }

    @Test
    fun partTwo() {
        assertThat(dayEight.partTwo(), `is`(25272L))
    }

}