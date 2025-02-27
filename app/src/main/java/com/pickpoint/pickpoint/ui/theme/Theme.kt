package com.pickpoint.pickpoint.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrototypePrimaryColor,
    onPrimary = DarkPrototypeOnPrimaryColor,
    background = DarkPrototypeBackgroundColor,
    secondary = DarkPrototypeSecondaryColor,
    tertiary = DarkPrototypeTertiaryColor
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrototypePrimaryColor,
    onPrimary = LightPrototypeOnPrimaryColor,
    background = LightPrototypeBackgroundColor,
    secondary = LightPrototypeSecondaryColor,
    tertiary = LightPrototypeTertiaryColor
)


@Composable
fun PickPointTheme(
    theme: AppTheme = AppTheme.LIGHT_PROTOTYPE,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDarkTheme = when (theme) {
        AppTheme.DARK_PROTOTYPE -> true
        AppTheme.LIGHT_PROTOTYPE -> false
        else -> false
    }
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val pointColors = when (theme){
        AppTheme.LIGHT_PROTOTYPE -> LightPrototypePointColors
        AppTheme.DARK_PROTOTYPE -> DarkPrototypePointColors
        else -> LightPrototypePointColors
    }

    CompositionLocalProvider(LocalPointColors provides pointColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = RobotoTypography,
            content = content
        )
    }
}