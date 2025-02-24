package com.pickpoint.pickpoint.ui.teammaker.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.common.component.NumberSettingComponent
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.common.component.ResultsComponent

@Composable
fun TeamMakerSettingContent(
    modifier: Modifier = Modifier,
    pointsToPick: Int,
    pointsToPickPlus: () -> Unit,
    pointsToPickMinus: () -> Unit,
    reset: () -> Unit,
    confirm: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            NumberSettingComponent(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp),
                label = "Points to Pick",
                currentNumber = pointsToPick,
                onPlusButtonClick = { pointsToPickPlus() },
                onMinusButtonClick = { pointsToPickMinus() }
            )

        }

        ResetConfirmButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.BottomCenter),
            reset = { reset() },
            confirm = { confirm() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TMSettingContentPreview() {
    TeamMakerSettingContent(
        pointsToPick = 1,
        pointsToPickPlus = {},
        pointsToPickMinus = {},
        reset = {},
        confirm = {}
    )
}