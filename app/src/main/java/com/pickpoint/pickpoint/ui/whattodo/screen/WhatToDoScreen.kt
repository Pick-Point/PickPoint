package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.ui.common.component.TopAppBar
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.common.util.getRandomElements
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.whattodo.component.WTDRandomPicker
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSettingContent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel

@Composable
fun WhatToDoScreen(
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()
    val confirmed by viewmodel.isConfirmed.collectAsState()
    val randomColors by viewmodel.randomColors.collectAsState()

    viewmodel.initRandomColors(LocalPointColors.current.getPointColorList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = "What to do",
                onNavigationClick = { },
                onActionClick = { }
            )
        },
    ) { innerPadding ->
        if (confirmed) {
            WTDSettingContent(
                modifier = Modifier.padding(innerPadding),
                count = count,
                onPlusButtonClick = { viewmodel.onPlusButtonClick() },
                onMinusButtonClick = { viewmodel.onMinusButtonClick() },
                resultList = resultList,
                onResultChanged = { index, result -> viewmodel.updateResultIndex(index, result) },
                reset = { viewmodel.reset() },
                confirm = { viewmodel.onConfirmButtonClick() }
            )
        } else {
            WTDRandomPicker(
                modifier = Modifier.padding(innerPadding),
                count = count,
                resultList = resultList,
                randomColors = randomColors
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen()
}