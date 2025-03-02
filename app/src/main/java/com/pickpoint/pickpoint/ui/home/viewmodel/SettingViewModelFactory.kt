package com.pickpoint.pickpoint.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager

class SettingViewModelFactory(
    private val dataStoreManager: DataStoreManager,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(application, dataStoreManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}