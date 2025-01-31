package com.pickpoint.pickpoint.ui.randompicker.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R

@Composable
fun DummyRandomPickerComponent(onClick: () -> Unit) {
    Button(onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEEEEEE),
            contentColor = Color(0xFF333333),
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.width(146.dp)
            .height(48.dp)
            .padding(start = 16.dp, top = 10.dp, end = 24.dp, bottom = 10.dp)
            .shadow(4.dp)
        ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_trash_can),
            contentDescription = "Reset"
        )
        Text("Reset",
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
    }

    Button(onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF333333),
            contentColor = Color(0xFFFFFFFF),
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.width(146.dp)
            .height(48.dp)
            .padding(start = 16.dp, top = 10.dp, end = 24.dp, bottom = 10.dp)
            .shadow(4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_check),
            contentDescription = "Confirm"
        )
        Text("Confirm",
            style = TextStyle(
                fontSize = 20.sp
            ))
        Spacer(modifier = Modifier.width(8.dp))
    }

    Button(onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF333333),
            contentColor = Color(0xFFFFFFFF),
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.width(300.dp)
            .height(48.dp)
            .padding(start = 16.dp, top = 10.dp, end = 24.dp, bottom = 10.dp)
            .shadow(4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_retry),
            contentDescription = "Cancel"
        )
        Text("Retry",
            style = TextStyle(
                fontSize = 20.sp
            ))
        Spacer(modifier = Modifier.width(8.dp))
    }
}

