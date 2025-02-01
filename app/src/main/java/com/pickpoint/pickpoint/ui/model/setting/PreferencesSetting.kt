package com.pickpoint.pickpoint.ui.model.setting

import androidx.annotation.StringRes
import com.pickpoint.pickpoint.R

enum class PreferencesSetting(
    @StringRes val res: Int, val value: String, val index: Int
) {
    REMEMBER_PREVIOUS_SETTINGS(
        R.string.remember_previous_settings,
        "Remember Previous Settings",
        0
    ),
    SOME_SETTINGS(R.string.some_settings, "Some Settings", 1);

}

