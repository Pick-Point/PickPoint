package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DragHandle() {
    BottomSheetDefaults.DragHandle(
        modifier = Modifier
            .padding(top = 14.dp, bottom = 5.dp),
        width = 120.dp,
        height = 4.dp,
        color = Color(0xFF333333)
    )
}

@Preview(showBackground = true)
@Composable
private fun DragHandlePreview() {
    DragHandle()
}