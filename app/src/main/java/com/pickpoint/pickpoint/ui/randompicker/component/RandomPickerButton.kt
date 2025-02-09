package com.pickpoint.pickpoint.ui.randompicker.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.robotoFontFamily

@Composable
fun ResetConfirmButton(reset: () -> Unit, confirm: () -> Unit) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

        Button(
            onClick = { reset() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFEEEEEE),
                contentColor = Color(0xFF333333),
            ),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.width(146.dp)
                .height(48.dp)
                .shadow(4.dp, RoundedCornerShape(100.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_trash_can),
                contentDescription = "Reset"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Reset",
                style = MaterialTheme.typography.labelLarge
            )
        }

        Button(
            onClick = { confirm() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF333333),
                contentColor = Color(0xFFFFFFFF),
            ),
            shape = RoundedCornerShape(100.dp),
            modifier = Modifier.width(146.dp)
                .height(48.dp)
                .shadow(4.dp, RoundedCornerShape(100.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Confirm",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Confirm",
                style = MaterialTheme.typography.labelLarge
            )
        }
            }

}

@Composable
fun RetryButton(retry:() -> Unit) {

    Button(
        onClick = { retry() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF333333),
            contentColor = Color(0xFFFFFFFF),
        ),
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.width(300.dp)
            .height(48.dp)
            .shadow(4.dp, RoundedCornerShape(100.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_retry),
            contentDescription = "Cancel"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "Retry",
            style = TextStyle(
                fontSize = 20.sp
            )
        )
    }

}


@Preview
@Composable
fun ResetConfirmButtonPreview() {
    ResetConfirmButton(reset = { /* Handle reset click */ }, confirm = { /* Handle confirm click */ })
}

@Preview
@Composable
fun RetryButtonPreview() {
    RetryButton(retry = { /* Handle retry click */ })
}