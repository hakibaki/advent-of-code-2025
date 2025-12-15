package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day10Test {
    
    private val dayTen = Day10()
    
    @Test
    fun partOne() {
        assertThat(dayTen.partOne(), `is`(7L))
    }

    @Test
    fun partTwo() {
        assertThat(dayTen.partTwo(), `is`(24L))
    }

}