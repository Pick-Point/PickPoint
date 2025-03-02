package com.pickpoint.pickpoint.ui.teammaker.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.NumberSettingComponent
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.common.component.ResultsComponent
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

@Composable
fun TeamMakerSettingContent(
    modifier: Modifier = Modifier,
    pointsToPick: Int,
    pointsToPickPlus: () -> Unit,
    pointsToPickMinus: () -> Unit,
    reset: () -> Unit,
    apply: () -> Unit
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NumberSettingComponent(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp),
                label = stringResource(id = R.string.points_to_pick),
                currentNumber = pointsToPick,
                onPlusButtonClick = { pointsToPickPlus() },
                onMinusButtonClick = { pointsToPickMinus() }
            )

        }
        ResetConfirmButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp)
                .padding(horizontal = 35.dp)
                .align(Alignment.BottomCenter),
            reset = { reset() },
            apply = { apply() }

        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TMSettingContentPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        TeamMakerSettingContent(
            pointsToPick = 1,
            pointsToPickPlus = {},
            pointsToPickMinus = {},
            reset = {},
            apply = {}
        )
    }
}