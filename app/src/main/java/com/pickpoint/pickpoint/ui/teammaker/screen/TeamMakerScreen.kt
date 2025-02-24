package com.pickpoint.pickpoint.ui.teammaker.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerGameComponent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerSettingContent
import com.pickpoint.pickpoint.ui.teammaker.component.TeamMakerTryAgain

@Composable
fun TeamMakerScreen(
    onNavigateBack: () -> Unit,
) {

    var totalCount by remember { mutableIntStateOf(4) }
    var pointsToPick by remember { mutableIntStateOf(1) }
    var confirmed by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (confirmed) {
                SecondaryTopAppBar(
                    title = "Game Settings",
                    onNavigationClick = onNavigateBack,
                )
            }
        },
    ) { innerPadding ->
        if (!confirmed) {
            TeamMakerSettingContent(
                modifier = Modifier.padding(innerPadding),
                totalCount = totalCount,
                pointsToPick = pointsToPick,
                totalPlus = {
                    if (totalCount < 10) totalCount += 1
                },
                totalMinus = {
                    if (totalCount > 1) totalCount -= 1
                },
                pointsToPickPlus = {
                    if (pointsToPick < 10) pointsToPick += 1
                },
                pointsToPickMinus = {
                    if (pointsToPick > 1) pointsToPick -= 1
                },
                reset = {
                    totalCount = 4
                    pointsToPick = 1
                },
                confirm = { confirmed = true }
            )
        } else {
            TeamMakerGameComponent(
                modifier = Modifier.padding(innerPadding),
                totalTeams = pointsToPick,
                resultDialog = { onRetry ->
                    TeamMakerTryAgain {
                        onRetry()
                        confirmed = false
                    }
                }
            )
        }
    }

}


@Preview
@Composable
fun SettingScreenPreview() {
    TeamMakerScreen(onNavigateBack = {})
}
