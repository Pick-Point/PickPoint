package com.pickpoint.pickpoint.ui.common.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

// 화면 터치가 발생할 때 타이머 시작을 담당하는 composable
@Composable
fun timerStartHandler(
    pointsToStart: Int = 2, // 시작하기 위한 포인트 개수
    timeToStart: Long = 2000, // 시작하기 위한 시간 (ms)
): Pair<SnapshotStateMap<Long, Pair<Offset, Color>>, SnapshotStateList<Pair<Offset, Color>>> {
    val touchPoints = remember { mutableStateMapOf<Long, Pair<Offset, Color>>() }
    val finalPoints = remember { mutableStateListOf<Pair<Offset, Color>>() }
    var isCountingDown by remember { mutableStateOf(false) }

    LaunchedEffect (touchPoints.keys.toSet()) {
        if (touchPoints.size >= pointsToStart && !isCountingDown) {
            isCountingDown = true
            // 100ms 단위로 타이머 진행하여 터치 개수 변화를 감지
            val initialPoints = touchPoints.keys.toSet()
            var elapsedTime = 0L
            while (elapsedTime < timeToStart){
                delay(100)
                elapsedTime += 100
                if (touchPoints.keys.toSet() != initialPoints){
                    isCountingDown = false
                    return@LaunchedEffect
                }
            }
            finalPoints.clear()
            finalPoints.addAll(touchPoints.values)
            isCountingDown = false
        }
    }

    return touchPoints to finalPoints
}
