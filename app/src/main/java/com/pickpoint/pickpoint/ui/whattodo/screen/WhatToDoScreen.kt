package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.DragHandle
import com.pickpoint.pickpoint.ui.common.component.GameTopAppBar
import com.pickpoint.pickpoint.ui.common.component.SettingsTopAppBar
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import com.pickpoint.pickpoint.ui.whattodo.component.WTDBottomSheetContent
import com.pickpoint.pickpoint.ui.whattodo.component.WTDGameComponent
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSeeResult
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSettingContent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatToDoScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    var isTapped by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    val confirmed by viewmodel.isConfirmed.collectAsState()

    viewmodel.initRandomColors(LocalPointColors.current.getPointColorList())

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            if (confirmed) {
                GameTopAppBar(
                    title = stringResource(id = R.string.what_to_do),
                    onBackClick = onNavigateBack,
                    onSettingClick = {
                        viewmodel.onSettingClick()
                    }
                )
            } else {
                SettingsTopAppBar(
                    title = stringResource(id = R.string.game_settings),
                    onNavigationClick = onNavigateBack
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (!confirmed) {
                WTDSettingContent(
                    modifier = Modifier.padding(innerPadding),
                    count = count,
                    onPlusButtonClick = { viewmodel.onPlusButtonClick() },
                    onMinusButtonClick = { viewmodel.onMinusButtonClick() },
                    resultList = resultList,
                    onResultChanged = { index, result ->
                        viewmodel.updateResultIndex(
                            index,
                            result
                        )
                    },
                    reset = { viewmodel.reset() },
                    confirm = { viewmodel.onConfirmButtonClick() }
                )
            } else {
                WTDGameComponent(
                    modifier = Modifier.padding(innerPadding),
                    totalPoints = count,
                    resultDialog = { onRetry ->
                        WTDSeeResult(
                            modifier = Modifier.padding(innerPadding),
                        ) {
                            showBottomSheet = true
                            coroutineScope.launch {
                                bottomSheetState.expand()
                            }
                        }
                    }
                )
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { 
                        showBottomSheet = false
                        coroutineScope.launch {
                            bottomSheetState.hide()
                        }
                    },
                    sheetState = bottomSheetState,
                    dragHandle = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            DragHandle()
                        }
                    }
                ) {
                    WTDBottomSheetContent(
                        modifier = Modifier
                            .fillMaxHeight(0.95f),
                        count = count,
                        resultList = resultList,
                        retryClick = {
                            viewmodel.setConfirmed(false)
                            showBottomSheet = false
                            coroutineScope.launch {
                                bottomSheetState.hide()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    PickPointTheme(theme = AppTheme.LIGHT_PROTOTYPE, dynamicColor = false) {
        WhatToDoScreen(
            modifier = Modifier,
            onNavigateBack = {

            }
        )
    }
}