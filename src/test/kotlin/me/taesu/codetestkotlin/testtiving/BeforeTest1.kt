package me.taesu.codetestkotlin.testtiving

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/07/22.
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class BeforeTest1 {
    @ParameterizedTest
    @MethodSource("input")
    fun `Test`(input: InputTivingTest1) {
        assertThat(solution(input.v)).isEqualTo(input.result)
    }


    fun solution(v: Array<IntArray>): IntArray {
        val positions = v.asSequence()
            .map { Position(it[0], it[1]) }
            .toList()

        val x = positions.asSequence().map { it.x }.groupBy { it }.filter { it.value.size == 1 }.keys.first()
        val y = positions.asSequence().map { it.y }.groupBy { it }.filter { it.value.size == 1 }.keys.first()
        println("$x $y")

        return intArrayOf(x, y)
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            InputTivingTest1(
                arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(3, 4),
                    intArrayOf(3, 10),
                ),
                intArrayOf(1, 10)
            ),

            InputTivingTest1(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(2, 2),
                    intArrayOf(1, 2),
                ),
                intArrayOf(2, 1)
            ),

            InputTivingTest1(
                arrayOf(
                    intArrayOf(1, 4),
                    intArrayOf(3, 10),
                    intArrayOf(1, 10),
                ),
                intArrayOf(3, 4)
            ),

            InputTivingTest1(
                arrayOf(
                    intArrayOf(1, Int.MAX_VALUE),
                    intArrayOf(3, 11),
                    intArrayOf(1, 11),
                ),
                intArrayOf(3, Int.MAX_VALUE)
            )
        )
    }
}

class InputTivingTest1(
    val v: Array<IntArray>,
    val result: IntArray
)

data class Position(
    val x: Int,
    val y: Int,
)