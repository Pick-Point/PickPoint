package com.pickpoint.pickpoint.ui.common.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PreferencesSetting
import com.pickpoint.pickpoint.ui.model.setting.ThemeSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "dataStore")

class DataStoreManager(private val context: Context) {

    private val themeKey = stringPreferencesKey("theme")
    private val languageKey = stringPreferencesKey("language")
    private val preferencesKey = stringPreferencesKey("preferences")

    suspend fun saveThemeSetting(setting: ThemeSetting) {
        context.dataStore.edit { theme ->
            theme[themeKey] = when (setting) {
                ThemeSetting.PROTOTYPE -> ThemeSetting.PROTOTYPE.value
                ThemeSetting.COMING_SOON -> ThemeSetting.COMING_SOON.value
            }
        }
    }

    suspend fun saveLanguageSetting(setting: LanguageSetting) {
        context.dataStore.edit { language ->
            language[languageKey] = when (setting) {
                LanguageSetting.KOREAN -> LanguageSetting.KOREAN.value
                LanguageSetting.ENGLISH -> LanguageSetting.ENGLISH.value
                LanguageSetting.JAPANESE -> LanguageSetting.JAPANESE.value
            }
        }
    }

    suspend fun savePreferencesSetting(setting: PreferencesSetting) {
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = when (setting) {
                PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS -> PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS.value
                PreferencesSetting.SOME_SETTINGS -> PreferencesSetting.SOME_SETTINGS.value
            }
        }
    }

    fun getThemeSetting(): Flow<ThemeSetting> {

        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                when (preferences[themeKey]) {
                    ThemeSetting.PROTOTYPE.value -> ThemeSetting.PROTOTYPE
                    ThemeSetting.COMING_SOON.value -> ThemeSetting.COMING_SOON
                    else -> ThemeSetting.PROTOTYPE
                }
            }
    }

    fun getLanguageSetting(): Flow<LanguageSetting> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                when (preferences[languageKey]) {
                    LanguageSetting.KOREAN.value -> LanguageSetting.KOREAN
                    LanguageSetting.ENGLISH.value -> LanguageSetting.ENGLISH
                    LanguageSetting.JAPANESE.value -> LanguageSetting.JAPANESE
                    else -> LanguageSetting.KOREAN
                }
            }
    }

    fun getPreferencesSetting(): Flow<PreferencesSetting> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                when (preferences[preferencesKey]) {
                    PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS.value -> PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS
                    PreferencesSetting.SOME_SETTINGS.value -> PreferencesSetting.SOME_SETTINGS
                    else -> PreferencesSetting.REMEMBER_PREVIOUS_SETTINGS
                }
            }
    }

}