package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/92334
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers92334 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(input: Input92334) {
        assertThat(solution(input.id_list, input.report, input.k)).isEqualTo(input.result)
    }

    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val reportedSet = report.asSequence()
            .map { it.split(" ", limit = 2) }
            .map {
                Report(it[0], it[1])
            }
            .toSet()
        // println(reportedSet) // 중복 신고가 제외된 신고 목록

        val suspendedIds = reportedSet.asSequence()
            .map { it.reported }
            // .filter { it in id_list }
            .groupBy { it }
            .filter { it.value.size >= k }
            .keys
        // println(suspendedIds)   // 계정 정지된 아이디 목록

        val idByResult = reportedSet.asSequence()
            .filter { it.reported in suspendedIds }
            .map { it.from }
            .groupBy { it }
            .mapValues { it.value.size }
        // println(idByResult) // 계정 별 처리 결과 메일을 받게 되는 횟수

        return id_list.map { idByResult[it] ?: 0 }.toIntArray()
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            Input92334(
                arrayOf("muzi", "frodo", "apeach", "neo"),
                arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
                2,
                intArrayOf(2, 1, 1, 0)
            ),
            Input92334(
                arrayOf("con", "ryan"),
                arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
                3,
                intArrayOf(0, 0)
            )
        )
    }
}

data class Report(
    val from: String,
    val reported: String,   // 신고 당한 사람
)

class Input92334(
    val id_list: Array<String>,
    val report: Array<String>,
    val k: Int,
    val result: IntArray
)