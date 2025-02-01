package com.pickpoint.pickpoint.ui.model.setting

import androidx.annotation.StringRes
import com.pickpoint.pickpoint.R

enum class LanguageSetting(
    @StringRes val value: Int, private val index: Int
) {
    KOREAN(R.string.korean, 0),
    ENGLISH(R.string.english, 1),
    JAPANESE(R.string.japanese, 2);

    fun getMatchedIndex(index: Int) = index == this.index
}

