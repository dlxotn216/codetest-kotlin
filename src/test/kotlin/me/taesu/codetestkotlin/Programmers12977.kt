package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/12977
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers12977 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(pair: Pair<IntArray, Int>) {
        assertThat(solution(pair.first)).isEqualTo(pair.second)
    }

    fun solution(nums: IntArray): Int {
        val sums = mutableListOf<Int>()
        for (i in nums.indices) {
            for (j in (i + 1) until nums.size) {
                for (k in (j + 1) until nums.size) {
                    (nums[i] + nums[j] + nums[k]).let {
                        if (isPrime(it)) {
                            sums += it
                            // println("""${nums[i]} + ${nums[j]} + ${nums[k]}""")
                        }
                    }
                }
            }
        }
        // println(sums)
        return sums.size
    }

    fun isPrime(n: Int): Boolean {
        for (i in 2 until n) {
            if (n % i == 0) {
                return false
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            intArrayOf(1, 2, 3, 4) to 1,
            intArrayOf(1, 2, 7, 6, 4) to 4,
            intArrayOf(7, 6, 2) to 0,
        )
    }
}
