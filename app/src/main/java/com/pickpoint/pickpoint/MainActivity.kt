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
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.home.screen.HomeScreen
import com.pickpoint.pickpoint.ui.home.viewmodel.HomeViewModel
import com.pickpoint.pickpoint.ui.theme.PickPointTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickPointTheme {
                val dataStoreManager = DataStoreManager(context = this)
                val homeViewModel = HomeViewModel(dataStoreManager = dataStoreManager)
                HomeScreen(viewModel = homeViewModel)
            }
        }
    }
}
