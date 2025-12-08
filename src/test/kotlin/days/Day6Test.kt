package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day6Test {

    private val daySix = Day6()

    @Test
    fun partOne() {
        assertThat(daySix.partOne(), `is`(4277556L))
    }

    @Test
    fun partTwo() {
        assertThat(daySix.partTwo(), `is`(3263827L))
    }

}