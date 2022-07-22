package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.*

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42587
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers42587 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(input: Input42587) {
        assertThat(solution(input.priorities, input.location)).isEqualTo(input.result)
    }

    fun solution(priorities: IntArray, location: Int): Int {
        val jobs = LinkedList(priorities.map { Job(UUID.randomUUID(), it) })
        val targetJob = jobs[location]

        var sequence = 1
        while (jobs.isNotEmpty()) {
            val job = jobs.poll()
            val hasMorePriority = jobs.any { it.priority > job.priority }
            if (hasMorePriority) {
                jobs.add(job)
            } else {
                if (job.id == targetJob.id) {
                    return sequence
                }
                sequence++
            }
        }
        return sequence
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            Input42587(
                intArrayOf(2, 1, 3, 2),
                2,
                1
            ),
            Input42587(
                intArrayOf(1, 1, 9, 1, 1, 1),
                0,
                5
            )
        )
    }
}

class Input42587(
    val priorities: IntArray,
    val location: Int,
    val result: Int
)

data class Job(
    val id: UUID,
    val priority: Int
)