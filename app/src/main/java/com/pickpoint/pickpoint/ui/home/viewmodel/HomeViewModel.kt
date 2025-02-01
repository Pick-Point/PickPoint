package com.pickpoint.pickpoint.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pickpoint.pickpoint.ui.common.util.DataStoreManager
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PreferencesSetting
import com.pickpoint.pickpoint.ui.model.setting.ThemeSetting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _themeSettingIndex = MutableStateFlow<Int>(0)
    val themeSettingIndex: StateFlow<Int> = _themeSettingIndex

    private val _languageSettingIndex = MutableStateFlow<Int>(0)
    val languageSettingIndex: StateFlow<Int> = _languageSettingIndex

    private val _preferencesSettingIndex = MutableStateFlow<Int>(0)
    val preferencesSettingIndex: StateFlow<Int> = _preferencesSettingIndex

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
            dataStoreManager.saveThemeSetting(
                when (_themeSettingIndex.value) {
                    ThemeSetting.PROTOTYPE.index -> ThemeSetting.PROTOTYPE
                    ThemeSetting.COMING_SOON.index -> ThemeSetting.COMING_SOON
                    else -> ThemeSetting.PROTOTYPE
                }
            )
            dataStoreManager.saveLanguageSetting(
                when (_languageSettingIndex.value) {
                    LanguageSetting.KOREAN.index -> LanguageSetting.KOREAN
                    LanguageSetting.ENGLISH.index -> LanguageSetting.ENGLISH
                    LanguageSetting.JAPANESE.index -> LanguageSetting.JAPANESE
                    else -> LanguageSetting.KOREAN
                }
            )
            dataStoreManager.savePreferencesSetting(
                when (_preferencesSettingIndex.value) {
                    PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS.index -> PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS
                    PreferencesSetting.SOME_SETTINGS.index -> PreferencesSetting.SOME_SETTINGS
                    else -> PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS
                }
            )
        }
    }


    private fun loadSettings() {

        viewModelScope.launch {
            combine(
                dataStoreManager.getThemeSetting(),
                dataStoreManager.getLanguageSetting(),
                dataStoreManager.getPreferencesSetting()
            ) { theme, language, preferences ->
                Triple(theme, language, preferences)
            }.collect { (theme, language, preferences) ->
                when (theme) {
                    ThemeSetting.PROTOTYPE -> {
                        _themeSettingIndex.value = ThemeSetting.PROTOTYPE.index
                    }

                    ThemeSetting.COMING_SOON -> {
                        _themeSettingIndex.value = ThemeSetting.COMING_SOON.index
                    }

                }

                when (language) {
                    LanguageSetting.KOREAN -> {
                        _languageSettingIndex.value = LanguageSetting.KOREAN.index
                    }

                    LanguageSetting.ENGLISH -> {
                        _languageSettingIndex.value = LanguageSetting.ENGLISH.index
                    }

                    LanguageSetting.JAPANESE -> {
                        _languageSettingIndex.value = LanguageSetting.JAPANESE.index
                    }
                }

                when (preferences) {
                    PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS -> {
                        _preferencesSettingIndex.value =
                            PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS.index
                    }

                    PreferencesSetting.SOME_SETTINGS -> {
                        _preferencesSettingIndex.value = PreferencesSetting.SOME_SETTINGS.index
                    }
                }
            }
        }
    }

    fun updateThemeSettingIndex(index: Int) {
        _themeSettingIndex.value = index
    }

    fun updateLanguageSettingIndex(index: Int) {
        _languageSettingIndex.value = index
    }

    fun updatePreferencesSettingIndex(index: Int) {
        _preferencesSettingIndex.value = index
    }

}