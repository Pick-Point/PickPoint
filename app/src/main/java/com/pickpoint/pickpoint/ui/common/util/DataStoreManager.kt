package com.pickpoint.pickpoint.ui.common.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "dataStore")

class DataStoreManager(private val context: Context) {

    private val themeKey = stringPreferencesKey("theme")
    private val languageKey = stringPreferencesKey("language")
    private val preferencesKey = stringPreferencesKey("preferences")

    suspend fun setThemeSetting(text: String) {
        context.dataStore.edit { theme ->
            theme[themeKey] = text
        }
    }

    suspend fun setLanguageSetting(text: String) {
        context.dataStore.edit { language ->
            language[languageKey] = text
        }
    }

    suspend fun setPreferencesSetting(text: String) {
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = text
        }
    }

    fun getThemeSetting(): Flow<String> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[themeKey] ?: "prototype"
            }
    }

    fun getLanguageSetting(): Flow<String> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[languageKey] ?: "한국어 (대한민국)"
            }
    }

    fun getPreferencesSetting(): Flow<String> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[preferencesKey] ?: "Remember Previous Settings"
            }
    }

}