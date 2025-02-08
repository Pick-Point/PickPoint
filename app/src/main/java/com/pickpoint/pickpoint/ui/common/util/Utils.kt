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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.pickpoint.pickpoint.ui.theme.PointColors
import kotlinx.coroutines.delay

// Point 색상을 담은 리스트를 반환하는 함수
fun PointColors.getPointColorList(): List<Color>{
    return listOf(
        pointColor1, pointColor2, pointColor3, pointColor4, pointColor5,
        pointColor6, pointColor7, pointColor8, pointColor9, pointColor10
    )
}

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
            delay(timeToStart)
            finalPoints.clear()
            finalPoints.addAll(touchPoints.values)
        }
    }

    return touchPoints to finalPoints
}
