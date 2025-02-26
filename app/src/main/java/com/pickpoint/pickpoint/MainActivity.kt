package com.pickpoint.pickpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.pickpoint.pickpoint.navigation.PickPointNavGraph
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.home.screen.HomeScreen
import com.pickpoint.pickpoint.ui.home.viewmodel.HomeViewModel
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val appTheme = AppTheme.LIGHT_PROTOTYPE // 이후에 저장된 테마를 불러오도록 수정 필요
            val appTheme = AppTheme.DARK_PROTOTYPE
            PickPointTheme(theme = appTheme, dynamicColor = false) {
                val dataStoreManager = DataStoreManager(context = this)
                val homeViewModel = HomeViewModel(dataStoreManager = dataStoreManager)
                val navController = rememberNavController()
                // homeViewModel을 전달
                PickPointNavGraph(
                    navController = navController,
                    homeViewModel = homeViewModel
                )
            }
        }

    }
}