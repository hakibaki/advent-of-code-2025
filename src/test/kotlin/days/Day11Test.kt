package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day11Test {
    
    private val dayEleven = Day11()
    
    @Test
    fun partOne() {
        assertThat(dayEleven.partOne(), `is`(5L))
    }

}