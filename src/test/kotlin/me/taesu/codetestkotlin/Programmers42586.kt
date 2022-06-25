package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/04.
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers42586 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(input: Programmers42586Input) {
        solution(input.progresses, input.speeds).forEach {
            print(it)
            print(",")
        }
        println()
    }

    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()


        val workingDays = progresses.mapIndexed { index, progress ->
            val speed = speeds[index]
            getWorkingDay(progress, speed)
        }
        var deployCount = 1
        var maxWorkingDay = workingDays.first()

        workingDays.subList(1, workingDays.size)
            .forEach { workingDay ->
                if (maxWorkingDay >= workingDay) {
                    deployCount++
                } else {
                    answer += deployCount
                    maxWorkingDay = workingDay
                    deployCount = 1
                }
            }

        answer += deployCount
        return answer.toIntArray()
    }

    private fun getWorkingDay(progress: Int, speed: Int): Int {
        val remaining = 100 - progress
        return (remaining / speed) + if ((remaining % speed) > 0) 1 else 0
    }

    companion object {
        @JvmStatic
        fun input() =
            listOf(
                Programmers42586Input(
                    intArrayOf(93, 30, 55),
                    intArrayOf(1, 30, 5),
                ),
                Programmers42586Input(
                    intArrayOf(95, 90, 99, 99, 80, 99),
                    intArrayOf(1, 1, 1, 1, 1, 1),
                ),
                Programmers42586Input(
                    intArrayOf(0, 0),
                    intArrayOf(100, 30),
                )
            )
    }
}

class Programmers42586Input(
    val progresses: IntArray,
    val speeds: IntArray,
)