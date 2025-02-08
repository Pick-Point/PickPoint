package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import com.pickpoint.pickpoint.ui.common.component.NumberSettingComponent
import com.pickpoint.pickpoint.ui.common.component.ResultsComponent

@Composable
fun WhatToDoScreen() {
    var count by remember { mutableIntStateOf(4) }
    val resultList = remember { mutableStateListOf<String>() }
    repeat(count) {
        resultList.add("")
    }

    Scaffold(
        topBar = { MainTopAppBar(title = "What to do") },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NumberSettingComponent(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(horizontal = 20.dp),
                label = "Total Points",
                currentNumber = count,
                onPlusButtonClick = {
                    if (count < 10) {
                        count++
                        resultList.add("")
                    }
                },
                onMinusButtonClick = {
                    if (count > 1) {
                        resultList.removeAt(--count)
                    }
                }
            )

            ResultsComponent(
                modifier = Modifier
                    .padding(top = 46.dp),
                title = "Results",
                count = count,
                resultList = resultList,
                onResultChanged = { index, result ->
                    resultList[index] = result
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen()
}