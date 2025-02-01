package com.pickpoint.pickpoint.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _themeSetting = MutableStateFlow<String>("")
    val themeSetting = _themeSetting

    private val _languageSetting = MutableStateFlow<String>("")
    val languageSetting = _languageSetting

    private val _preferencesSetting = MutableStateFlow<String>("")
    val preferencesSetting = _preferencesSetting

    private fun loadSettings() {
        viewModelScope.launch {
            loadThemeSetting()
            loadLanguageSetting()
            loadPreferencesSetting()
        }
    }

    private suspend fun loadThemeSetting() {
        dataStoreManager.getThemeSetting().collect { theme ->
            _themeSetting.value = theme
        }
    }

    private suspend fun loadLanguageSetting() {
        dataStoreManager.getLanguageSetting().collect { language ->
            _languageSetting.value = language
        }
    }

    private suspend fun loadPreferencesSetting() {
        dataStoreManager.getPreferencesSetting().collect { preferences ->
            _preferencesSetting.value = preferences
        }
    }
}