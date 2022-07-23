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
class BeforeTest3 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(pair: Pair<Int, IntArray>) {
        assertThat(solution(pair.first)).isEqualTo(pair.second)
    }

    fun solution(n: Int): IntArray {
        val answer = IntArray(n, { it + 1 })

        return answer
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            3 to intArrayOf(1, 2, 3)
        )
    }
}
