package com.pickpoint.pickpoint.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _themeSetting = MutableStateFlow<String>("")
    val themeSetting: StateFlow<String> = _themeSetting

    private val _languageSetting = MutableStateFlow<String>("")
    val languageSetting: StateFlow<String> = _languageSetting

    private val _preferencesSetting = MutableStateFlow<String>("")
    val preferencesSetting: StateFlow<String> = _preferencesSetting

    init {
        loadSettings()
    }

    // reset 버튼의 onClick 이벤트로 설정하면 됨.
    fun resetSettings() {
        loadSettings()
    }

    // confirm 버튼의 onClick 이벤트로 설정하면 됨.
    fun saveSettings() {
        viewModelScope.launch {
            saveThemeSettings()
            saveLanguageSettings()
            savePreferencesSettings()
        }
    }

    private suspend fun saveThemeSettings() {
        dataStoreManager.setThemeSetting(themeSetting.value)
    }

    private suspend fun saveLanguageSettings() {
        dataStoreManager.setLanguageSetting(languageSetting.value)
    }

    private suspend fun savePreferencesSettings() {
        dataStoreManager.setPreferencesSetting(preferencesSetting.value)
    }


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