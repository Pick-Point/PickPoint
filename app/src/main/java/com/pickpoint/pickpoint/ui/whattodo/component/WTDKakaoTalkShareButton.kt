package com.pickpoint.pickpoint.ui.whattodo.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel

@Composable
fun KakaotalkShareButton(
    modifier: Modifier = Modifier,
    viewModel: WhatToDoViewmodel?  // ViewModel 추가
) {
    val context = LocalContext.current

    // 카카오톡 공유 버튼
    Button(
        onClick = {
            viewModel?.let {
                it.shareTextToKakaoTalk(context)
            }
        },
        shape = RoundedCornerShape(100.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .shadow(4.dp, RoundedCornerShape(100.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFEE500), // 카카오톡 노란색
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "카카오톡으로 공유하기",
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun KakaotalkShareButtonPreview() {
    KakaotalkShareButton(viewModel = null)
}