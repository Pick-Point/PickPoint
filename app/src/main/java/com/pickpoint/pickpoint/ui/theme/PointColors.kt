package com.pickpoint.pickpoint.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class PointColors(
    val pointColor1: Color,
    val pointColor2: Color,
    val pointColor3: Color,
    val pointColor4: Color,
    val pointColor5: Color,
    val pointColor6: Color,
    val pointColor7: Color,
    val pointColor8: Color,
    val pointColor9: Color,
    val pointColor10: Color,
    val pointTextColor : Color,
)

/*
* LIGHT_PROTOTYPE 에서 사용할 Point 색상들
* */
val LightPrototypePointColors = PointColors(
     pointColor1 = Color(0xFFFF9900),
     pointColor2 = Color(0xFF7FACFF),
     pointColor3 = Color(0xFF90DBA6),
     pointColor4 = Color(0xFFE88081),
     pointColor5 = Color(0xFFF6FF92),
     pointColor6 = Color(0xFFD08EFF),
     pointColor7 = Color(0xFF6DFFF3),
     pointColor8 = Color(0xFF2C61FF),
     pointColor9 = Color(0xFFAEAEAE),
     pointColor10 =Color(0xFF000000),
     pointTextColor = Color(0xFFFFFFFF)
)


/*
* DARK_PROTOTYPE 에서 사용할 Point 색상들
* TODO: 색상 지정
* */
val DarkPrototypePointColors = PointColors(
    pointColor1 = Color(0xFFFF9900),
    pointColor2 = Color(0xFF7FACFF),
    pointColor3 = Color(0xFF90DBA6),
    pointColor4 = Color(0xFFE88081),
    pointColor5 = Color(0xFFF6FF92),
    pointColor6 = Color(0xFFD08EFF),
    pointColor7 = Color(0xFF6DFFF3),
    pointColor8 = Color(0xFF2C61FF),
    pointColor9 = Color(0xFFAEAEAE),
    pointColor10 =Color(0xFF000000),
    pointTextColor = Color(0xFFFFFFFF)
)

val LocalPointColors = staticCompositionLocalOf { LightPrototypePointColors }