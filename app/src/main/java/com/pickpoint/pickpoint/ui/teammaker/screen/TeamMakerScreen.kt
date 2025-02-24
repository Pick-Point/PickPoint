package com.pickpoint.pickpoint.ui.teammaker.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TeamMakerScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "Team Maker",
                    modifier = modifier,
                    onNavigationClick = { /*뒤로가기 동작*/ },
                )
            }
        ) {
                paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Tap to start!", fontSize = 28.sp,
                    modifier = Modifier
                        .background(Color.White)

                )
            }
        }
    }
}

@Preview
@Composable
fun TeamMakerScreenPreview() {
    TeamMakerScreen()
}
