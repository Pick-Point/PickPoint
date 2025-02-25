package com.pickpoint.pickpoint.ui.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun NumberSettingComponent(
    modifier: Modifier = Modifier,
    label: String,
    currentNumber: Int,
    onPlusButtonClick: (Int) -> Unit,
    onMinusButtonClick: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentNumber.toString(),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Row {
                    //- 버튼
                    OutlinedButton(
                        onClick = {
                            onMinusButtonClick(currentNumber)
                        },
                        shape = CircleShape, // 원형 모양
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary), // 테두리 설정
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .size(30.dp) // 버튼 크기 조정
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_number_setting_minus),
                            contentDescription = "-",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    //+ 버튼
                    OutlinedButton(
                        onClick = {
                            onPlusButtonClick(currentNumber)
                        },
                        shape = CircleShape, // 원형 모양
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary), // 테두리 설정
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .size(30.dp) // 버튼 크기 조정
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "-",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NumberSettingComponentPreview() {
    var count by rememberSaveable { mutableIntStateOf(0) }
    val label = "Total Points"
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        NumberSettingComponent(
            label = label,
            currentNumber = count,
            onPlusButtonClick = {
                if (count < 10) {
                    count++
                } else {
                    //Toast
                }
            },
            onMinusButtonClick = {
                if (count > 1) {
                    count--
                } else {
                    //Toast
                }
            }
        )
    }
}