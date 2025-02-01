package com.pickpoint.pickpoint.ui.model.setting

import androidx.annotation.StringRes
import com.pickpoint.pickpoint.R

enum class ThemeSetting(
    @StringRes val value: Int, private val index: Int
) {
    PROTOTYPE(R.string.prototype, 0),
    COMING_SOON(R.string.coming_soon, 1);

    fun getMatchedIndex(index: Int) = index == this.index
}

