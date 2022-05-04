package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers60057 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(input: String) {
        getMinimumLength(input)
    }

    private fun getMinimumLength(input: String): Int {
        val maxTryDigitLength = input.length / 2
        val minOf = (1..(maxTryDigitLength)).map {
            compress(input, it).apply {
                // println("$input to $it result: [$this]")
            }
        }.minByOrNull { it.length }
        // println("minof $minOf , ${minOf?.length}")
        return minOf?.length ?: input.length
    }

    private fun compress(input: String, digit: Int): String {
        val result = mutableListOf<String>()

        var token = input.substring(0, digit)
        var cursor = digit
        var matchedCount = 1
        while (true) {
            val nextToken = getNextToken(input, cursor, digit)
            if (nextToken == null) {    // 맨 끝에 도달 한 경우
                result += if (matchedCount > 1) matchedCount.toString() else ""
                result += token
                break
            }
            if (token == nextToken) {
                matchedCount++
            } else {
                result += if (matchedCount > 1) matchedCount.toString() else ""
                result += token

                token = nextToken
                matchedCount = 1
            }
            cursor += digit
        }
        if (cursor <= input.lastIndex) {    // 뒤에 남아있는 문자열이 있는 경우
            result += input.substring(cursor)
        }
        return result.joinToString("")
    }

    private fun getNextToken(input: String, endIndexExclusive: Int, digit: Int): String? {
        return try {
            input.substring(endIndexExclusive, endIndexExclusive + digit)
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            "aabbaccc",             // 7    2a2ba3c
            "ababcdcababcdcd",      // 9    2ababcdcd
            "abcabcdede",               // 8
            "abcabcabcabcdededededede", // 14
            "xababcdcdababcdcd",        // 17
        )
    }
}
