package com.pickpoint.pickpoint.ui.home.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
fun HomeButton(onClick: () -> Unit, Text: String = "Random Picker") {
        Button(
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEEEEEE),
                contentColor = Color(0xFF333333),
            ),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier
                .width(181.dp)
                .height(59.dp)
                .shadow(2.dp, RoundedCornerShape(100.dp))
                .shadow(3.dp, RoundedCornerShape(100.dp))
        ) {
            Text(
                text = Text,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }

}

@Preview
@Composable
fun HomeButtonPreview(){
    HomeButton(onClick = { /* Handle click */ })
}
