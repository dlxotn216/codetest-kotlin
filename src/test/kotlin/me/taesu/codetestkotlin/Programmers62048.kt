package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.math.max
import kotlin.math.min

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://programmers.co.kr/learn/courses/30/lessons/62048
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers62048 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(pair: Pair<Int, Int>) {
        println(
            pair.first.toLong() * pair.second.toLong() - (getEffectSquareCount(pair.first.toLong() to pair.second.toLong()))
        )
    }

    private fun getEffectSquareCount(pair: Pair<Long, Long>): Long {
        val greatestCommonFactor = getGreatestCommonFactor(pair)

        /*
            가로지르는 경로가 반복되는 조각의 가로, 세로
            2 * 3 ->  2 + 3 - 1 = = 4
            | o |   |
            | o | o |
            |   | o |

            4 * 3 ->  4 + 3 - 1 = = 6
            | o | O |   |   |
            |   | o | o |   |
            |   |   | o | o |
         */
        val rectanglePieceWidth = pair.first / greatestCommonFactor
        val rectanglePieceHeight = pair.second / greatestCommonFactor
        val effectSquareCount = rectanglePieceWidth + rectanglePieceHeight - 1
        return effectSquareCount * greatestCommonFactor
    }

    // private fun getGreatestCommonFactor(pair: Pair<Long, Long>): Long {
    //     val minNumber = min(pair.first, pair.second)
    //     (minNumber downTo 1).forEach {
    //         if (pair.first % it == 0L && pair.second % it == 0L) {
    //             return it
    //         }
    //     }
    //     return 1
    // }

    private fun getGreatestCommonFactor(pair: Pair<Long, Long>): Long {
        var maxNumber = max(pair.first, pair.second)
        var minNumber = min(pair.first, pair.second)
        while (minNumber != 0L) {
            val mod = maxNumber % minNumber
            maxNumber = minNumber
            minNumber = mod
        }
        return maxNumber
    }


    companion object {
        @JvmStatic
        fun input() = arrayOf(
            8 to 12,
            4 to 4,
            2 to 8,
            5 to 3,
            6 to 10,
        )
    }
}
