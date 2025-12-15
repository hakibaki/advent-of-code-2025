package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day9Test {

    private val dayNine = Day9()

    @Test
    fun partOne() {
        assertThat(dayNine.partOne(), `is`(50L))
    }

    @Test
    fun partTwo() {
        assertThat(dayNine.partTwo(), `is`(24L))
    }

}