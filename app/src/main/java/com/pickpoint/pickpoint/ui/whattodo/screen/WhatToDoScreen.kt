package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.R
import com.pickpoint.pickpoint.ui.common.component.DragHandle
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import com.pickpoint.pickpoint.ui.common.component.SecondaryTopAppBar
import com.pickpoint.pickpoint.ui.common.util.getPointColorList
import com.pickpoint.pickpoint.ui.theme.LightPrototypeOnPrimaryColor
import com.pickpoint.pickpoint.ui.theme.LocalPointColors
import com.pickpoint.pickpoint.ui.whattodo.component.WTDBottomSheetContent
import com.pickpoint.pickpoint.ui.whattodo.component.WTDGameComponent
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSeeResult
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSettingContent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatToDoScreen(
    onNavigateBack: () -> Unit,
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    var isTapped by remember { mutableStateOf(false) }

    val confirmed by viewmodel.isConfirmed.collectAsState()

    viewmodel.initRandomColors(LocalPointColors.current.getPointColorList())

    LaunchedEffect(showSheet) {
        if (showSheet) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.hide()
        }
    }
    LaunchedEffect(scaffoldState) {
        showSheet = !scaffoldState.bottomSheetState.isVisible
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (confirmed) {
                MainTopAppBar(
                    title = "What to do",
                    leftIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_main_top_back),
                            contentDescription = null,
                            tint = LightPrototypeOnPrimaryColor
                        )
                    },
                    rightIcon = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = null,
                            tint = LightPrototypeOnPrimaryColor
                        )
                    }
                )
            } else {
                SecondaryTopAppBar(
                    title = "Game Settings",
                    onNavigationClick = onNavigateBack
                )
            }
        },
        sheetContent = {
            WTDBottomSheetContent(
                modifier = Modifier
                    .fillMaxHeight(0.91f),
                count = count,
                resultList = resultList,
                retryClick = {
                    showSheet = false
                    viewmodel.setConfirmed(false)
                }
            )
        },
        sheetPeekHeight = if (isTapped) 53.dp else 0.dp,
        sheetDragHandle = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                DragHandle()
            }
        }


    ) { innerPadding ->
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
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        coroutineScope.launch {
                            showSheet = true
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen({})
}