package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/02.
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class TestClass {
    @ParameterizedTest
    @MethodSource("input")
    fun `test`(input: Int) {
        println(getMaxDistance(input))
    }

    private fun getMaxDistance(input: Int): Int {
        val digitResult = getDigits(input)

        if (digitResult.oneCount <= 1 || digitResult.zeroCount == 0) {
            return 0
        } else if (digitResult.zeroCount == 1 && digitResult.reversedDigits.first() != 0) {
            return 1
        }

        var maxDistance = 0
        var distance = 0
        (0 until digitResult.reversedDigits.size).reversed().forEach {
            val number = digitResult.reversedDigits[it]
            if (number == 0) {
                distance++
            } else {
                maxDistance = maxOf(distance, maxDistance)
                distance = 0
            }
        }
        return maxDistance
    }

    private fun getDigits(input: Int): DigitResult {
        var number = input

        var zeroCount = 0
        var oneCount = 0
        val digits = arrayListOf<Int>()
        while (number > 0) {
            val mod = number % 2
            if (mod == 0) {
                zeroCount++
            } else {
                oneCount++
            }
            digits += mod
            number /= 2
        }

        return DigitResult(digits, zeroCount, oneCount)
    }

    companion object {
        @JvmStatic
        fun input() =
            listOf(19, 16, 9, 6, 519, 32, 2, 147, 483, 647, 328)
    }

}

class DigitResult(
    val reversedDigits: ArrayList<Int>,
    val zeroCount: Int,
    val oneCount: Int,
)