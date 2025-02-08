package com.pickpoint.pickpoint.ui.common.util

import androidx.compose.ui.graphics.Color
import com.pickpoint.pickpoint.ui.theme.PointColors

//Point 색상을 담은 리스트를 반환하는 함수
fun PointColors.getPointColorList(): List<Color>{
    return listOf(
        pointColor1, pointColor2, pointColor3, pointColor4, pointColor5,
        pointColor6, pointColor7, pointColor8, pointColor9, pointColor10
    )
}