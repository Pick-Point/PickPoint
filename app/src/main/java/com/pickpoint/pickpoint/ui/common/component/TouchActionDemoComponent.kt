package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import kotlin.math.roundToInt


@Composable
fun TouchActionDemoComponent(modifier: Modifier = Modifier) {
    // 각 터치의 포인터 ID와 위치 정보를 저장하는 상태 리스트
    val touchPoints = remember { mutableStateMapOf<Long, Pair<Offset, Color>>() }
    val pointColorList = LocalPointColors.current.getPointColorList()
    val usedColors = remember { mutableStateListOf<Color>()}
    val pointSize = 100

    Box(
        modifier = Modifier
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
                                if (pointerId !in touchPoints){
                                    val availableColor = pointColorList.filter { it !in usedColors }.randomOrNull()
                                    if (availableColor != null){
                                        usedColors.add(availableColor)
                                        touchPoints[pointerId] = pointerInputChange.position to availableColor
                                    }
                                }else{
                                    //기존 위치 업데이트
                                    touchPoints[pointerId] = pointerInputChange.position to touchPoints[pointerId]!!.second
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
        Text("TouchActionDemoComponent")
        // 현재 활성화된 각 터치에 대해 Point composable 표시
        touchPoints.forEach { (_, data) ->
            val (position, color) = data
            // offset을 이용해 터치한 위치에 Point를 배치
            CircleButton(
                modifier = Modifier.offset {
                    IntOffset(
                        (position.x - (pointSize/2).dp.toPx()).roundToInt(),
                        (position.y - (pointSize/2).dp.toPx()).roundToInt()
                    )
                },
                pointSize = pointSize,
                color = color,
            ) {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TouchLogicTestPreview() {
    TouchActionDemoComponent()
}