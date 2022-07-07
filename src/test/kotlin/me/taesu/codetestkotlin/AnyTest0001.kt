package me.taesu.codetestkotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors

/**
 * Created by itaesu on 2022/07/07.
 *
 * https://jeong-pro.tistory.com/212
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class AnyTest0001 {
    @Test
    fun `취미별 인원 수를 구하라`() {
        // given
        val result = Files.lines(Paths.get("src/test/resources/AnyTest0001.csv")).skip(1)
            .map { line -> resolveMember(line) }
            .filter { it != null }
            .flatMap {
                val member = it!!
                Arrays.stream(member.hobbies).map { hobby ->
                    hobby to member
                }
            }
            .collect(
                Collectors.groupingBy(
                    { it.first },
                    Collectors.counting()
                )
            )

        // println(result)
        assertThat(result["스포츠댄스"]).isEqualTo(1L)
        assertThat(result["족구"]).isEqualTo(1L)
        assertThat(result["당구"]).isEqualTo(1L)
        assertThat(result["개발"]).isEqualTo(2L)
        assertThat(result["야구"]).isEqualTo(1L)
        assertThat(result["피아노"]).isEqualTo(1L)
        assertThat(result["농구"]).isEqualTo(1L)
        assertThat(result["축구"]).isEqualTo(1L)
    }

    private fun resolveMember(line: String): Member? {
        val elements = line.split(",")
        return try {
            Member(
                elements[0].trim(),
                elements[1].trim(),
                elements[2].trim(),
            )
        } catch (e: Exception) {
            null
        }
    }

}

data class Member(
    val name: String,
    val hobbyString: String,
    val introduce: String
) {
    val hobbies
        get(): Array<String> = try {
            hobbyString.split(":").toTypedArray()
        } catch (e: Exception) {
            emptyArray()
        }
}