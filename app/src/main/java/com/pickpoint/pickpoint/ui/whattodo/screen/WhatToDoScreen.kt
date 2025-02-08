package com.pickpoint.pickpoint.ui.whattodo.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pickpoint.pickpoint.ui.common.component.NumberSettingComponent
import com.pickpoint.pickpoint.ui.common.component.ResetConfirmButton
import com.pickpoint.pickpoint.ui.common.component.ResultsComponent
import com.pickpoint.pickpoint.ui.common.component.TopAppBar
import com.pickpoint.pickpoint.ui.whattodo.component.WTDSettingContent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel

@Composable
fun WhatToDoScreen(
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()

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
            reset = { viewmodel.reset() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen()
}