package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/06/25.
 *
 * https://programmers.co.kr/learn/courses/30/lessons/43105
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class Programmers43105 {

    @ParameterizedTest
    @MethodSource("input")
    fun test(pair: Pair<Array<Array<Int>>, Int>) {
        assertThat(getMaxSum(pair.first)).isEqualTo(pair.second)
    }


    private fun getMaxSum(triangle: Array<Array<Int>>): Int {
        triangle.forEachIndexed { level, ints ->
            ints.forEachIndexed { index, number ->
                triangle[level][index] += getMaxParent(triangle, level, index)
            }
        }

        return triangle.last().maxOf { it }
    }

    /**
     *      7            ->         7
     *    3   8          ->     10     15
     *  8   1   0        -> 18  (11 vs 16) 15
     */
    private fun getMaxParent(
        triangle: Array<Array<Int>>,
        level: Int,
        index: Int
    ) = maxOf(
        try {
            triangle[level - 1][index - 1]
        } catch (e: Exception) {
            0
        }, try {
            triangle[level - 1][index]
        } catch (e: Exception) {
            0
        }
    )

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            arrayOf(
                arrayOf(7),     // 7
                arrayOf(3, 8),  // 10, 15
                arrayOf(8, 1, 0),   // 18, 11, 16, 15
                arrayOf(2, 7, 4, 4), // 20, 25, 18, 15, 20, 19
                arrayOf(4, 5, 2, 6, 5),
            ) to 30,

            arrayOf(
                arrayOf(2),
                arrayOf(3, 8),       // 5, 10
                arrayOf(8, 1, 9),     // 13, 6, 11, 19
                arrayOf(2, 7, 1, 4),   // 15, 20, 13, 7, 12, 15, 23
                arrayOf(4, 6, 2, 6, 5),
            ) to 29
        )
    }
}