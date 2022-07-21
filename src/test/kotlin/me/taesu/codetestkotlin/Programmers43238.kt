package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/07/21.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class Programmers43238 {
    @ParameterizedTest
    @MethodSource("inputs")
    fun `test`(pair: Pair<Input, Long>) {
        assertThat(solution(pair.first.n, pair.first.times)).isEqualTo(pair.second)
    }

    fun solution(n: Int, times: IntArray): Long {
        var min = 1L
        var max: Long = (times.maxOf { it }.toLong()) * n
        var minimalTime = max

        while (min <= max) {
            val mid = (min + max) / 2
            val howManyProcess = howManyProcess(times, mid)
            if (howManyProcess >= n) {
                max = mid - 1
                minimalTime = mid
            } else {
                min = mid + 1
            }

        }
        return minimalTime
    }

    fun howManyProcess(times: IntArray, minutes: Long): Long {
        return times.sumOf { minutes / it }
    }

    companion object {
        @JvmStatic
        fun inputs() = listOf(
            Input(6, intArrayOf(7, 10)) to 28,      // (1..60) 30, (1..29) 15, (16..29) 22, (23..29) 26, (27, 29) 28
            Input(6, intArrayOf(1, 10)) to 6,
            Input(6, intArrayOf(9, 10)) to 30,
            Input(6, intArrayOf(11, 10)) to 33,
        )
    }
}

class Input(
    val n: Int,
    val times: IntArray
)