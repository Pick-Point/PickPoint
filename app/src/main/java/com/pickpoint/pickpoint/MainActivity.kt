package com.pickpoint.pickpoint

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.pickpoint.pickpoint.navigation.PickPointNavGraph
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.home.viewmodel.SettingViewModel
import com.pickpoint.pickpoint.ui.theme.AppTheme
import com.pickpoint.pickpoint.ui.theme.PickPointTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var isLoading = true // splash 유지 여부 결정(이후에 viewModel 에서 값 가져오도록 수정?)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Splash
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{
            // 이 람다 안의 값이 true이면 Splash 화면이 유지됨
            isLoading
        }
        // 2초후에 Splash 화면 종료
        lifecycleScope.launch {
            // 이후에 여기에서 테마 정보 가져오도록 하면 될듯
            delay(2000L)
            isLoading = false
        }
        setContent {
            val appTheme = AppTheme.LIGHT_PROTOTYPE // 이후에 저장된 테마를 불러오도록 수정 필요
//           val appTheme = AppTheme.DARK_PROTOTYPE
            PickPointTheme(theme = appTheme, dynamicColor = false) {
                val dataStoreManager = DataStoreManager(context = this)
                val settingViewModel = SettingViewModel(
                    application = application,
                    dataStoreManager = dataStoreManager,
                )
                val navController = rememberNavController()
                // homeViewModel을 전달
                PickPointNavGraph(
                    navController = navController,
                    settingViewModel = settingViewModel
                )
            }
        }

    }

}