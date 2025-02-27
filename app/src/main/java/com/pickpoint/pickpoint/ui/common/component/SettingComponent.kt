package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.model.setting.ThemeSetting
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

/**
 * @param*/
@Composable
fun SettingComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    settingRes: List<Int>,
    checkedIndex: Int = 0,
    onClick: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .height(36.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        Card(
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .shadow(elevation = 4.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(size = 8.dp))
            ) {
                settingRes.forEachIndexed { index, res ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(size = 8.dp))
                            .clickable { onClick(index) }
                            .padding(10.dp)
                            .height(24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(id = res),
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        if (index == checkedIndex) {
                            Icon(
                                painter = painterResource(R.drawable.ic_setting_check_24),
                                contentDescription = "checked",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    if (index != settingRes.size - 1) {
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .background(MaterialTheme.colorScheme.tertiary)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingComponentPreview() {
    var checkedIndex by remember { mutableIntStateOf(0) }

    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        SettingComponent(
            title = "Language",
            settingRes = ThemeSetting.entries.map { it.res },
            checkedIndex = checkedIndex,
            onClick = { checkedIndex = it }
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun SettingComponentPreviewFull() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        SettingComponent(
            title = "Language",
            settingRes = ThemeSetting.entries.map { it.res },
            checkedIndex = 0
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 800
)
@Composable
private fun SettingComponentPreviewWide() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        SettingComponent(
            title = "Language",
            settingRes = ThemeSetting.entries.map { it.res },
            checkedIndex = 0
        )
    }
}