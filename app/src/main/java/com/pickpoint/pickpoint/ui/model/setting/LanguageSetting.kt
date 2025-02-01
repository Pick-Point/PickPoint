package com.pickpoint.pickpoint.ui.model.setting

import androidx.annotation.StringRes
import com.pickpoint.pickpoint.R

enum class LanguageSetting(
    @StringRes val res: Int, val value: String, private val index: Int
) {
    KOREAN(R.string.korean, "한국어 (대한민국)", 0),
    ENGLISH(R.string.english, "English", 1),
    JAPANESE(R.string.japanese, "日本語", 2);

}

