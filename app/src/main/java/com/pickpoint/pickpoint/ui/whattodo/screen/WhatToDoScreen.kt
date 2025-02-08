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
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSettingContent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel

@Composable
fun WhatToDoScreen(
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()
    var confirmed by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "What to do",
                onNavigationClick = { },
                onActionClick = { }
            )
        },
    ) { innerPadding ->
        WTDSettingContent(
            modifier = Modifier.padding(innerPadding),
            count = count,
            onPlusButtonClick = { viewmodel.onPlusButtonClick() },
            onMinusButtonClick = { viewmodel.onMinusButtonClick() },
            resultList = resultList,
            onResultChanged = { index, result -> viewmodel.updateResultIndex(index, result) },
            reset = { viewmodel.reset() },
            confirm = { confirmed = true }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen()
}