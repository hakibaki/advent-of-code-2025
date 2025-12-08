package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day7Test {

    private val daySeven = Day7()

    @Test
    fun partOne() {
        assertThat(daySeven.partOne(), `is`(21))
    }

    @Test
    fun partTwo() {
        assertThat(daySeven.partTwo(), `is`(40))
    }

}