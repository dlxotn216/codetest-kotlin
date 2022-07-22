package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.*

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/49189
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers49189 {
    @ParameterizedTest
    @MethodSource("input")
    fun test(input: Input49189) {
        assertThat(solution(input.n, input.edge)).isEqualTo(input.result)
    }


    fun solution(n: Int, edge: Array<IntArray>): Int {
        val visited = mutableSetOf<Int>()
        val distanceMap = mutableMapOf<Int, Long>()
        val edgeMap = mutableMapOf<Int, MutableSet<Int>>()
        edge.forEach {
            if (edgeMap[it[0]] == null) {
                edgeMap[it[0]] = mutableSetOf()
            }
            if (edgeMap[it[1]] == null) {
                edgeMap[it[1]] = mutableSetOf()
            }
            edgeMap[it[0]]!!.add(it[1])
            edgeMap[it[1]]!!.add(it[0])
        }

        visited.add(1)
        dfs(
            n = n,
            nodeToVisit = 1,
            visited = visited,
            distanceMap = distanceMap,
            edgeMap = edgeMap,
        )
        println(distanceMap)
        val maxDistance = distanceMap.values.maxOf { it }
        return distanceMap.filter { it.value == maxDistance }.keys.size
    }

    fun dfs(
        n: Int,
        nodeToVisit: Int,
        visited: MutableSet<Int>,
        distanceMap: MutableMap<Int, Long>,
        edgeMap: Map<Int, MutableSet<Int>>
    ) {
        val stack = Stack<Int>()
        val ints = edgeMap[nodeToVisit] ?: emptySet()
        ints.filter { it !in visited }.forEach {
            stack.push(it)
            visited.add(it)
            distanceMap[it] = (distanceMap[nodeToVisit] ?: 0) + 1
        }

        while (stack.isNotEmpty()) {
            dfs(n, stack.pop(), visited, distanceMap, edgeMap)
        }
    }

    companion object {
        @JvmStatic
        fun input() = arrayOf(
            Input49189(
                arrayOf(
                    intArrayOf(3, 6),
                    intArrayOf(4, 3),
                    intArrayOf(3, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 2),
                    intArrayOf(2, 4),
                    intArrayOf(5, 2),
                ),
                6,
                3
            ),
            Input49189(
                arrayOf(
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(2, 3),
                ),
                3,
                2
            ),
            Input49189(
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(3, 6),
                    intArrayOf(6, 2),
                ),
                6,
                1
            ),
            Input49189(
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(3, 6),
                    intArrayOf(6, 2),
                    intArrayOf(6, 1),
                ),
                6,
                1
            ),
            Input49189(
                arrayOf(
                    intArrayOf(1, 5),
                    intArrayOf(1, 2),
                    intArrayOf(6, 5),
                    intArrayOf(6, 2),
                ),
                6,
                1
            ),
            Input49189(
                arrayOf(
                    intArrayOf(1, 3),
                    intArrayOf(3, 6),
                    intArrayOf(6, 5),
                    intArrayOf(1, 5),
                ),
                6,
                1
            ),
            Input49189(
                arrayOf(
                    intArrayOf(1, 6),
                    intArrayOf(1, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 4),
                    intArrayOf(1, 5),
                ),
                6,
                5
            ),
            Input49189(
                arrayOf(
                    intArrayOf(3, 6),
                    intArrayOf(4, 3),
                    intArrayOf(3, 2),
                    intArrayOf(1, 3),
                    intArrayOf(1, 2),
                    intArrayOf(2, 4),
                    intArrayOf(1, 4),
                    intArrayOf(5, 2),
                ),
                6,
                2
            )
        )
    }
}

class Input49189(
    val edge: Array<IntArray>,
    val n: Int,
    val result: Int
)