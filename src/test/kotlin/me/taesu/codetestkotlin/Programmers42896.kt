package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/06/30.
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class Programmers42896 {
    @ParameterizedTest
    @MethodSource("input")
    fun `test`(pair: Pair<Int, Int>) {
        println(pair)
        println(solution(pair.first, pair.second))

    }

    // n = 5
    // number = 12
    // 1 = 1
    // 2 = 1 + 1 + (repeat)
    // 3 = 1 + 2, 2 + 1 + repeat
    // 4 = 1 + 3, 2 + 2, 3 + 1 + repeat
    // 5 = 1 + 4, 2 + 3, 3 + 2,  4 + 1 + repeat
    fun solution(N: Int, number: Int): Int {
        if (N == number) return 1

        var usedCount = 2
        val possibleSetMap = mutableMapOf(
            1 to setOf(N),
            2 to generateCases(N, N, N, usedCount).toSet()
        )

        while (usedCount < 9) {
            val possibleNumbers = possibleSetMap[usedCount] ?: emptySet()
            // println("$usedCount -> $possibleNumbers")
            if (number in possibleNumbers) {
                return usedCount
            }

            usedCount++
            possibleSetMap[usedCount] = (1..(usedCount / 2)).flatMap {
                val set1 = possibleSetMap[it] ?: emptySet()
                val set2 = possibleSetMap[usedCount - it] ?: emptySet()
                set1.flatMap { n1 ->
                    set2.flatMap { n2 ->
                        generateCases(n1, n2, N, usedCount)
                    }
                }
            }.toSet()
        }

        return -1
    }

    // set(1) + set(3)과 set(3) + set(1)을 모두 구함
    fun generateCases(n1: Int, n2: Int, N: Int, usedCount: Int): List<Int> {
        return mutableListOf(
            n1 + n2,
            n1 * n2,
            n1 - n2, n2 - n1,
            N.toString().repeat(usedCount).toInt()
        ).apply {
            if (n1 != 0) {
                add(n2 / n1)
            }
            if (n2 != 0) {
                add(n1 / n2)
            }
        }
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            5 to 12,
            5 to 555,
            5 to 110,   // 4
            7 to 154,   // 4
            5 to 20,
            5 to 35,
            5 to 2,
            5 to 4,
            5 to 3,
            5 to 111,
            2 to 11,
            7 to 311,
            6 to 6,
            6 to 7,
            9 to 12,
            5 to 31168
        )
    }
}