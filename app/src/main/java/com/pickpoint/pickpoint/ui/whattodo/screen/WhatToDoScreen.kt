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
import com.pickpoint.pickpoint.ui.common.component.MainTopAppBar
import com.pickpoint.pickpoint.ui.common.component.NumberSettingComponent
import com.pickpoint.pickpoint.ui.common.component.ResultsComponent
import com.pickpoint.pickpoint.ui.whattodo.viewmodel.WhatToDoViewmodel

@Composable
fun WhatToDoScreen(
    viewmodel: WhatToDoViewmodel = viewModel()
) {

    val count by viewmodel.count.collectAsState()
    val resultList by viewmodel.resultList.collectAsState()

    Scaffold(
        topBar = { MainTopAppBar(title = "What to do") },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {

                NumberSettingComponent(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .padding(horizontal = 20.dp),
                    label = "Total Points",
                    currentNumber = count,
                    onPlusButtonClick = {
                        if (count < 10)
                            viewmodel.onPlusButtonClick()
                    },
                    onMinusButtonClick = {
                        if (count > 1)
                            viewmodel.onMinusButtonClick()
                    }
                )

                ResultsComponent(
                    modifier = Modifier
                        .padding(top = 46.dp),
                    title = "Results",
                    count = count,
                    resultList = resultList,
                    onResultChanged = { index, result ->
                        viewmodel.updateResultIndex(index, result)
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WhatToDoScreenPreview() {
    WhatToDoScreen()
}