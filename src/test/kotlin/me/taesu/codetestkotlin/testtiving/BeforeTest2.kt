package me.taesu.codetestkotlin.testtiving

/**
 * Created by itaesu on 2022/07/22.
 *
 * @author Lee Tae Su
 * @version codetest-kotlin
 * @since codetest-kotlin
 */
class BeforeTest2 {

}

fun main() {
    val readLine = readLine()
    val n = readLine!!.split(' ').map(String::toInt).firstOrNull() ?: 0
    (1..n).forEach {
        repeat(it) {
            print("*")
        }
        println()
    }
}