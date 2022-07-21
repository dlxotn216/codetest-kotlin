package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers43165 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(input: Input43165) {
        assertThat(solution(input.numbers, input.target)).isEqualTo(input.result)

        assertThat(dfs(input.numbers, 0, 0, input.target).let { count }).isEqualTo(input.result)
    }

    var count = 0
    fun dfs(numbers: IntArray, depth: Int, sum: Int, target: Int) {
        if (numbers.size == depth) {
            if (sum == target) {
                count++
            }
            return
        }

        dfs(numbers, depth + 1, sum + numbers[depth], target)
        dfs(numbers, depth + 1, sum - numbers[depth], target)
    }

    fun solution(numbers: IntArray, target: Int): Int {
        val number = List(numbers.size) { 2 }.reduce { acc, i -> acc * i }
        var count = 0
        for (i in 1 until number) {
            val indexes = getIndexes(i, numbers)
            val sum = getSum(indexes, numbers)
            // println("""$digit $indexes $sum""")
            if (sum == target) {
                count++
            }
        }

        return count
    }

    private fun getIndexes(i: Int, numbers: IntArray): MutableList<Int> {
        var digit = i
        val indexes = MutableList(numbers.size) { 0 }
        var index = 0
        while (digit > 0) {
            indexes[index++] += digit % 2
            digit /= 2
        }
        return indexes
    }

    private fun getSum(
        indexes: MutableList<Int>,
        numbers: IntArray
    ): Int {
        return indexes.mapIndexed { index, it ->
            if (it == 0) {
                numbers[index]
            } else {
                -numbers[index]
            }
        }.sum()
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            Input43165(intArrayOf(1, 1, 1, 1, 1), 3, 5),
            Input43165(intArrayOf(4, 1, 2, 1), 4, 2),
        )
    }
}

class Input43165(
    val numbers: IntArray,
    val target: Int,
    val result: Int
)