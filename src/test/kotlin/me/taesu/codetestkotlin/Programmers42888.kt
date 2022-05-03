package me.taesu.codetestkotlin

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Created by itaesu on 2022/05/03.
 *
 * https://programmers.co.kr/learn/courses/30/lessons/42888?language=kotlin
 *
 * @author Lee Tae Su
 * @version ConsentV3 v1.0 wB202203
 * @since ConsentV3 v1.0 wB202203
 */
class Programmers42888 {

    @ParameterizedTest
    @MethodSource("input")
    fun test(input: Input) {
        println(getResult(input))
    }

    private fun getResult(input: Input): Array<String> {
        val userIdNickNameMap = mutableMapOf<String, String>()
        return input.records
            .map { it.split(" ") }
            .map {
                Record(it.toTypedArray()).apply {
                    if (command == Command.ENTER || command == Command.CHANGE) {
                        userIdNickNameMap[userId] = it[2]
                    }
                }
            }
            .filter { it.command != Command.CHANGE }
            .map { getMessage(userIdNickNameMap[it.userId] ?: "", it.command) }
            .toTypedArray()
    }

    private fun getMessage(nickName: String, command: Command): String {
        return when (command) {
            Command.ENTER -> "${nickName}님이 들어왔습니다."
            Command.LEAVE -> "${nickName}님이 나갔습니다."
            else -> throw IllegalArgumentException("$command is invalid command.")
        }
    }


    companion object {
        @JvmStatic
        fun input(): List<Input> =
            listOf(
                Input(
                    arrayOf(
                        "Enter uid1234 Muzi",
                        "Enter uid4567 Prodo",
                        "Leave uid1234",
                        "Enter uid1234 Prodo",
                        "Change uid4567 Ryan"
                    ),
                    arrayOf("Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다.")
                )
            )
    }

    class Input(val records: Array<String>, val results: Array<String>)

    class Record(raws: Array<String>) {
        val command = Command.valueOf(raws[0].uppercase())
        val userId = raws[1]
    }

    enum class Command {
        ENTER,
        LEAVE,
        CHANGE,
        ;
    }
}
