package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.hamcrest.core.IsNull.notNullValue
import org.junit.jupiter.api.Test

class Day2Test {

    private val dayTwo = Day2()

    @Test
    fun testPartOne() {
        assertThat(dayTwo.partOne(), `is`(1227775554))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwo.partTwo(), `is`(4174379265))
    }

    @Test
    fun testIsNumberInvalid() {
        assertThat(dayTwo.isNumberInvalid(1212), `is`(true))
        assertThat(dayTwo.isNumberInvalid(123123), `is`(true))
        assertThat(dayTwo.isNumberInvalid(1234), `is`(false))
        assertThat(dayTwo.isNumberInvalid(1111), `is`(true))
        assertThat(dayTwo.isNumberInvalid(12341234), `is`(true))
        assertThat(dayTwo.isNumberInvalid(9999), `is`(true))
    }
}
