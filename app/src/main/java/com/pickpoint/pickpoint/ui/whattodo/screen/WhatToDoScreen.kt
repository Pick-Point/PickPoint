package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.ui.common.component.DragHandle
import com.pickpoint.pickpoint.ui.common.component.TopAppBar
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.common.util.getRandomElements
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.whattodo.component.WTDBottomSheetContent
import com.pickpoint.pickpoint.ui.whattodo.component.WTDRandomPicker
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSettingContent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatToDoScreen(
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()
    val confirmed by viewmodel.isConfirmed.collectAsState()
    val randomColors by viewmodel.randomColors.collectAsState()

    viewmodel.initRandomColors(LocalPointColors.current.getPointColorList())

    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                title = "What to do",
                onNavigationClick = { },
                onActionClick = { }
            )
        },
        sheetContent = {
            WTDBottomSheetContent(
                modifier = Modifier
                    .height(
                        LocalConfiguration.current.screenHeightDp.dp - 56.dp
                    ),
                count = count,
                resultList = resultList,
                retryClick = { }
            )
        },
        sheetPeekHeight = 53.dp,
        sheetDragHandle = { DragHandle() }


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