package com.pickpoint.pickpoint.ui.whattodo.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.ui.common.component.RetryButton

@Composable
fun WTDBottomSheetContent(
    modifier: Modifier = Modifier,
    count: Int,
    resultList: List<String>,
    retryClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(top = 6.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = "Results",
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 40.sp,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            LazyColumn(
                modifier = Modifier
                    .padding(top = 34.dp)
                    .fillMaxWidth()
            ) {
                items(count = count) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${index + 1}. ${resultList[index]}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                letterSpacing = 0.25.sp
                            ),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                    HorizontalDivider(
                        Modifier.padding(bottom = 10.dp),
                        color = Color(0xFFD9D9D9)
                    )
                }
            }
        }

        RetryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp),
            retry = { retryClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WTDBottomSheetContentPreview() {
    WTDBottomSheetContent(
        count = 5,
        resultList = listOf("result1", "result2", "result3", "result4", "result5"),
        retryClick = { }
    )
}