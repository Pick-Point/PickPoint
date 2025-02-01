package com.pickpoint.pickpoint.ui.model.setting

import androidx.annotation.StringRes
import com.pickpoint.pickpoint.R

enum class PreferencesSetting(
    @StringRes val value: Int, private val index: Int
) {
    REMEMBER_PREVIOUS_SETTINGS(R.string.remember_previous_settings, 0),
    SOME_SETTINGS(R.string.some_settings, 1);

    fun getMatchedIndex(index: Int) = index == this.index
}

