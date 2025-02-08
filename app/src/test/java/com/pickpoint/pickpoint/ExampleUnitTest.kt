package com.pickpoint.pickpoint

import com.pickpoint.pickpoint.ui.common.util.getRandomElements
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `랜덤 개수 반환 테스트`() {
        val list = listOf(1, 2, 3, 4, 5)
        val randomList = list.getRandomElements(3)
        assertEquals(3, randomList.size)
    }

    @Test
    fun `최대 개수가 넘는 숫자를 인자로 넘길 때 셔플된 리스트가 반환되는지 확인`() {
        val list = listOf(1, 2, 3, 4, 5)
        val randomList = list.getRandomElements(10)
        assertEquals(5, randomList.size)
    }

    @Test
    fun `사이즈의 개수만큼 인자로 넘길 때 셔플된 리스트가 반환되는지 확인`() {
        val list = listOf(1, 2, 3, 4, 5)
        val randomList = list.getRandomElements(5)
        assertEquals(5, randomList.size)
    }
}