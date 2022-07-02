package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/07/02.
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class ProgrammerUNKNOWN0001 {
    @ParameterizedTest
    @MethodSource("input")
    fun `test`(pair: Pair<String, String>) {
        val input = pair.first
        val countPerString = mutableMapOf<String, Int>()
        var previousStepCombinations = listOf<String>()
        input.forEachIndexed { index, char ->
            previousStepCombinations = if (index > 0) {
                listOf(char.toString()) + previousStepCombinations.map { "$it$char" }
            } else {
                listOf(char.toString())
            }

            previousStepCombinations.forEach {
                countPerString[it] = (countPerString[it] ?: 0) + 1
            }
        }

        // 출현빈도가 가장 많은 문자열들
        val candidateKeywords = with(countPerString.maxOf { it.value }) {
            countPerString.filter { it.value == this }
        }

        // 키워드 중 최대 길이
        val maxLengthOfKeyword = candidateKeywords.keys.maxOf { it.length }

        val keywords = candidateKeywords.keys.filter { it.length == maxLengthOfKeyword }
        val result = keywords.fold(input) { acc, keyword ->
            acc.replace(keyword, "", false)
        }
        assertThat(result).isEqualTo(pair.second)
    }

    companion object {
        // a
        // b, ab
        // c, bc, abc
        // a, ca, bca, abca
        @JvmStatic
        fun input() = arrayOf(
            "abcabcdefabc" to "def",
            "abxdeydeabz" to "xyz",
            "aaaaaaaaaaa" to "",

            )
    }
}