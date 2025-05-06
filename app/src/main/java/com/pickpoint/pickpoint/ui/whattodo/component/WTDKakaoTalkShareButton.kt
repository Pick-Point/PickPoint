package com.pickpoint.pickpoint.ui.whattodo.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
        Row() {
            // 카카오톡 아이콘 (R.drawable.ic_kakaotalk으로 아이콘을 추가해야 합니다)
            Image(
                painter = painterResource(id = R.drawable.ic_kakaotalk), // 카카오톡 아이콘 리소스 추가 필요
                contentDescription = "카카오톡 아이콘",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "카카오톡으로 공유하기",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun KakaotalkShareButtonPreview() {
    KakaotalkShareButton(viewModel = null)
}