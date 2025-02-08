package com.pickpoint.pickpoint.ui.common.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.common.util.timerStartHandler
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


@Composable
fun TouchActionComponent(
    modifier: Modifier = Modifier,
    pointsToStart: Int = 2,
    resultDialog: @Composable ((onRetry: () -> Unit) -> Unit)? = null, // 카운트다운 끝난 후 결과 다이얼로그
    onCountdownDone: (List<Pair<Offset, Color>>) -> Unit
) {
    val pointColorList = LocalPointColors.current.getPointColorList()
    val pointSize = 100
    val timeToStart: Long = 2000 //2초
    val usedColors = remember { mutableStateListOf<Color>() }
    var countdown by remember { mutableStateOf<Int?>(null)}
    var isGameActive by remember { mutableStateOf(true) } // 게임 진행 여부
    var showResultDialog by remember { mutableStateOf(false) } // 결과 다이얼로그 표시 여부

    val (touchPoints, finalPoints) = timerStartHandler(
        pointsToStart = pointsToStart,
        timeToStart = timeToStart,
    )


    // 카운트다운
    LaunchedEffect(touchPoints.keys.toSet()){
        if (touchPoints.size >= pointsToStart){
            delay(timeToStart)
            for (i in 3 downTo 1){
                countdown = i
                delay(1000)
            }
            isGameActive = false
            countdown = null
            showResultDialog = true

            finalPoints.clear()
            finalPoints.addAll(touchPoints.values)
        }
    }

    val resetGame: () -> Unit = {
        isGameActive = true
        showResultDialog = false
        countdown = null
        touchPoints.clear()
        finalPoints.clear()
        usedColors.clear()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            // pointerInput을 이용해 터치 이벤트를 감지
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        event.changes.forEach { pointerInputChange ->
                            val pointerId = pointerInputChange.id.value
                            if (pointerInputChange.pressed) {
                                // 이미 있는 포인터면 색상 유지, 없으면 랜덤 색상 할당
                                if (pointerId !in touchPoints) {
                                    val availableColor =
                                        pointColorList.filter { it !in usedColors }.randomOrNull()
                                    if (availableColor != null) {
                                        usedColors.add(availableColor)
                                        touchPoints[pointerId] =
                                            pointerInputChange.position to availableColor
                                    }
                                } else {
                                    //기존 위치 업데이트
                                    touchPoints[pointerId] =
                                        pointerInputChange.position to touchPoints[pointerId]!!.second
                                }
                            } else {
                                touchPoints[pointerId]?.second?.let { usedColors.remove(it) }
                                touchPoints.remove(pointerId)
                            }
                        }
                    }
                }
            }
    ) {
        Text("TouchActionComponent")
        // 현재 활성화된 각 터치에 대해 Point composable 표시
        if (isGameActive) {
            touchPoints.forEach { (_, data) ->
                val (position, color) = data
                // offset을 이용해 터치한 위치에 Point를 배치
                CircleButton(
                    modifier = modifier.offset {
                        IntOffset(
                            (position.x - (pointSize / 2).dp.toPx()).roundToInt(),
                            (position.y - (pointSize / 2).dp.toPx()).roundToInt()
                        )
                    },
                    pointSize = pointSize,
                    color = color,
                ) {

                }
            }
        }
        // 카운트다운 끝난 후 유지되는 Point 표시
        finalPoints.forEach { (position, color) ->
            CircleButton(
                modifier = modifier.offset {
                    IntOffset(
                        (position.x - (pointSize / 2).dp.toPx()).roundToInt(),
                        (position.y - (pointSize / 2).dp.toPx()).roundToInt()
                    )
                },
                pointSize = pointSize,
                color = color,
            ) {
            }
        }
        // 카운트다운 표시
        countdown?.let{
            Text(
                text = it.toString(),
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = modifier.align(Alignment.Center)
            )
        }

        if (showResultDialog) {
            Log.d("TouchActionComponent", "showResultDialog is true")
//            Button(
//                onClick = { resetGame() },
//                modifier = Modifier.align(Alignment.Center)
//            ) {
//                Text("Retry",
//                    style = MaterialTheme.typography.labelLarge,
//                    color = MaterialTheme.colorScheme.onPrimary
//                )
//            }

            resultDialog?.invoke(resetGame)
        }
    }

}



@Preview(showBackground = true)
@Composable
private fun TouchLogicTestPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        Column(modifier = Modifier.fillMaxSize()) {
            TouchActionComponent(
                onCountdownDone = {},
                resultDialog = { onRetry ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { onRetry() },
                            modifier = Modifier.align(Alignment.Center)
                        ) {
                            Text("Retry",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )
        }
    }
}