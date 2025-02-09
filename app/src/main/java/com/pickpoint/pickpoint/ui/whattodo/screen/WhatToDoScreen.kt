package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.ui.common.component.DragHandle
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
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
    val randomColors by viewmodel.randomColors.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var showSheet by remember { mutableStateOf(false) }
    var isTapped by remember { mutableStateOf(false) }

    val confirmed by viewmodel.isConfirmed.collectAsState()

    viewmodel.initRandomColors(LocalPointColors.current.getPointColorList())

    if (showSheet) {
        LaunchedEffect(Unit) {
            scaffoldState.bottomSheetState.expand()
            showSheet = false
        }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainTopAppBar(
                title = "What to do",
                onNavigationClick = { }
            )
        },
        sheetContent = {
            WTDBottomSheetContent(
                modifier = Modifier
                    .fillMaxHeight(0.91f),
                count = count,
                resultList = resultList,
                retryClick = { }
            )
        },
        sheetPeekHeight = if (isTapped) 53.dp else 0.dp,
        sheetDragHandle = {
            DragHandle()
        }


    ) { innerPadding ->
        if (!confirmed) {
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
                isTapped = isTapped,
                startClick = { isTapped = true },
                resultList = resultList,
                randomColors = randomColors,
                expandBottomSheet = { showSheet = true }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen()
}