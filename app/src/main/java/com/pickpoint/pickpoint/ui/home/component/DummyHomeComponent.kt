package com.pickpoint.pickpoint.ui.home.component

import android.graphics.drawable.shapes.Shape
import android.text.style.BackgroundColorSpan
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DummyHomeComponent(onClick: () -> Unit) {
    Button(onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEEEEEE),
            contentColor = Color(0xFF333333),
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.width(181.dp)
            .height(59.dp)
            .shadow(2.dp)
            .shadow(3.dp)
    ) {
        Text("Random Picker",
            style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewDummyHomeComponent() {
//    DummyHomeComponent(onClick = {})
//}
//@Composable
//fun DummyHomeComponent(onClick: () -> Unit = {}) {
//    Button(
//        onClick = { onClick() },
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color(0xFFEEEEEE),
//            contentColor = Color(0xFF333333)
//        ),
//        shape = RoundedCornerShape(100.dp)
//
//    ) {
//        Text("Random Picker")
//    }
//} preview 미리보기를 위한 코드

