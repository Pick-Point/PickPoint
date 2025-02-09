package com.pickpoint.pickpoint.ui.whattodo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.PointColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

/**
 * innerPadding 받아야 함 !
 * */
@Composable
fun WTDRandomPicker(
    modifier: Modifier = Modifier,
    isTapped: Boolean,
    startClick: () -> Unit,
    count: Int,
    resultList: List<String>,
    randomColors: List<Color>,
    expandBottomSheet: () -> Unit
) {
    var timer by remember { mutableIntStateOf(3) }

    if (!isTapped) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { startClick() }
        ) {
            Text(
                text = "Tap to Start!",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF333333),
                )
            )
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            LaunchedEffect(Unit) {
                CoroutineScope(coroutineContext).run {
                    repeat(3) {
                        delay(1000)
                        timer -= 1
                    }
                }
            }

            if (timer > 0) {
                Text(
                    text = timer.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(
                        fontSize = 57.sp,
                        lineHeight = 64.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF333333),
                    )
                )
                // TODO : Random Picker View & Logic

            } else {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = "See Result",
                        style = TextStyle(
                            fontSize = 57.sp,
                            lineHeight = 64.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF333333),
                        )
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .width(52.dp)
                            .height(52.dp)
                            .background(
                                color = Color(0xFFEEEEEE),
                                shape = RoundedCornerShape(size = 100.dp)
                            )
                            .align(Alignment.CenterHorizontally),
                        onClick = { expandBottomSheet() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_forward),
                            contentDescription = null
                        )
                    }
                }
            }


        }
    }

}

@Preview
@Composable
private fun WTDRandomPickerPreview() {
    WTDRandomPicker(
        count = 4,
        isTapped = false,
        startClick = { },
        resultList = listOf(
            "벌칙1",
            "벌칙2",
            "벌칙3",
            "벌칙4"
        ),
        randomColors = listOf(
            Color(0xFFFF9900),
            Color(0xFF7FACFF),
            Color(0xFF90DBA6),
            Color(0xFF000000)
        ),
        expandBottomSheet = { }
    )
}