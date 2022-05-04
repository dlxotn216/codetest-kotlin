package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://programmers.co.kr/learn/courses/30/lessons/12899
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers12899 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(number: Int) {
        println("$number is ${getDigit(number)}")
    }

    // 3진법에서 0, 1, 2 대신 1, 2, 4를 사용
    // 단 3의 배수는 -1로 감소 후 끝자리를 4로..
    fun getDigit(number: Int): String {
        var result = ""
        var target = number

        while (target > 0) {
            when (val mod = target % 3) {
                0 -> {
                    target -= 1
                    result = "4$result"
                }
                else -> {
                    result = mod.toString() + result
                }
            }
            target /= 3
        }
        return result
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            1, 2, 3, 4, 5, 11, 12, 13, 20, 14, 6000, 27
        )
    }
}
