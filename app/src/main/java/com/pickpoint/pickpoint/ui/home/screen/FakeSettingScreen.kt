package com.pickpoint.pickpoint.ui.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpoint.pickpoint.ui.common.component.SettingComponent
import com.pickpoint.pickpoint.ui.home.viewmodel.HomeViewModel
import com.pickpoint.pickpoint.ui.model.setting.LanguageSetting
import com.pickpoint.pickpoint.ui.model.setting.PreferencesSetting
import com.pickpoint.pickpoint.ui.model.setting.ThemeSetting

@Composable
fun FakeSettingScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    val themeSettingIndex by viewModel.themeSettingIndex.collectAsStateWithLifecycle()
    val languageSettingIndex by viewModel.languageSettingIndex.collectAsStateWithLifecycle()
    val preferencesSettingIndex by viewModel.preferencesSettingIndex.collectAsStateWithLifecycle()


    Column(
        modifier = modifier
            .clipToBounds()
    ) {
        SettingComponent(
            modifier = Modifier.padding(top = 20.dp),
            title = "Theme",
            settingRes = ThemeSetting.entries.map { it.res },
            checkedIndex = themeSettingIndex,
            onClick = { viewModel.updateThemeSettingIndex(it) }
        )
        SettingComponent(
            modifier = Modifier.padding(top = 20.dp),
            title = "Language",
            settingRes = LanguageSetting.entries.map { it.res },
            checkedIndex = languageSettingIndex,
            onClick = { viewModel.updateLanguageSettingIndex(it) }
        )
        SettingComponent(
            modifier = Modifier.padding(top = 20.dp),
            title = "Preferences",
            settingRes = PreferencesSetting.entries.map { it.res },
            checkedIndex = preferencesSettingIndex,
            onClick = { viewModel.updatePreferencesSettingIndex(it) }
        )
        Spacer(Modifier.size(50.dp))

        Row {
            Button(
                onClick = { viewModel.resetSettings() },
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Reset")
            }
            Spacer(Modifier.size(20.dp))

            Button(
                onClick = { viewModel.saveSettings() },
                modifier = Modifier.padding(20.dp)
            ) {
                Text(text = "Confirm")
            }
        }
    }
}