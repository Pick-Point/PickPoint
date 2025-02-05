package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

/*
* 예시용 Composable
* */
@Composable
fun ThemeDemoComponent(modifier: Modifier = Modifier) {
    val pointColorList = LocalPointColors.current.getPointColorList()
    var selectedColorInt by rememberSaveable { mutableStateOf(pointColorList.random().toArgb()) }
    val selectedColor = Color(selectedColorInt)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = "Demo For\nThemes",
            textAlign = TextAlign.Center,
            /*
            * Theme에서 MaterialTheme의 typography에 직접 정의한 스타일을 적용했기 때문에
            * 아래와 같이 사용하면 자동으로 Type에서 설정한 폰트가 적용된다
            * */
            style = MaterialTheme.typography.displayLarge,
            color = selectedColor
        )
        Button(
            onClick = {
                selectedColorInt = pointColorList.random().toArgb()
            },
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "colors",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeDemoComponentPreview() {
    //Preview에서 보기 위해 parameter로 직접 Theme을 지정했고, Preview에서는 dynamicColor를 지원하지 않아서 false로 설정
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        ThemeDemoComponent()
    }
}