package com.pickpoint.pickpoint.ui.randompicker.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton


@Composable
fun SettingScreen(
    modifier: Modifier,
    totalPoints: Int,
    pointsToPick: Int,
    onPointsChange: (Int) -> Unit,
    onPointsPickChange: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                modifier = modifier,
                title = "Game Setting",
                onNavigationClick = { /*뒤로가기 동작*/ },
            )
        },

        ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                val context = LocalContext.current
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Total Points", fontSize = 14.sp, letterSpacing = 0.1.sp,
                    lineHeight = 20.sp

                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                        .height(50.dp)
                        .width(320.dp)
                        .shadow(1.dp, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {

                    Text(
                        text = totalPoints.toString(),
                        fontSize = 28.sp,
                        lineHeight = 36.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)
                    )

                    OutlinedButton(
                        onClick = { if (totalPoints > 0) onPointsChange(totalPoints - 1) else Toast.makeText(context, "Total Points는 0보다 커야합니다.", Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                    ) {
                        Text(text = "−", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedButton(
                        onClick = {if (totalPoints < 9) onPointsChange(totalPoints + 1) else Toast.makeText(context, "최대 9점까지 설정 가능합니다.", Toast.LENGTH_SHORT).show()},
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                    ) {
                        Text(text = "+", fontSize = 20.sp)
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Points to Pick",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.1.sp,
                    modifier = Modifier.height(23.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
                        .height(50.dp)
                        .width(320.dp)
                        .shadow(1.dp, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = pointsToPick.toString(),
                        fontSize = 28.sp,
                        lineHeight = 36.sp,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 10.dp)

                    )

                    OutlinedButton(
                        onClick = { if (pointsToPick > 0) onPointsPickChange(pointsToPick - 1) else Toast.makeText(context, "Points to Pick은 0보다 커야합니다.", Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                    ) {
                        Text(text = "−", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    OutlinedButton(
                        onClick = {if (pointsToPick  < 9) onPointsPickChange(pointsToPick + 1)  else Toast.makeText(context,"최댓값은 9입니다.",Toast.LENGTH_SHORT).show() },
                        modifier = Modifier.size(30.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Color.Black),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                    ) {
                        Text(text = "+", fontSize = 20.sp)
                    }
                }
            }
            ResetConfirmButton(
                reset = {onPointsChange(4)
                    onPointsPickChange(1)},
                confirm = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 14.dp)
                    .padding(horizontal = 30.dp)
                    .align(Alignment.BottomCenter),
            )
        }

    }

}

@Preview
@Composable
fun SettingScreenPreview() {
    var totalPoints by remember { mutableIntStateOf(4) }
    var pointsToPick by remember { mutableIntStateOf(1) }

    SettingScreen(
        modifier = Modifier,
        totalPoints = totalPoints,
        pointsToPick = pointsToPick,
        onPointsChange = { totalPoints = it },
        onPointsPickChange = { pointsToPick = it }
    )

}