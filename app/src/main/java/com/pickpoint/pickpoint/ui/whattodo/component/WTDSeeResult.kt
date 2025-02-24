package com.pickpoint.pickpoint.ui.whattodo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R

@Composable
fun WTDSeeResult(modifier: Modifier = Modifier, setShowSheet: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
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
                onClick = setShowSheet
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_forward),
                    contentDescription = null
                )
            }
        }
    }
}