package com.pickpoint.pickpoint.ui.theme

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.pickpoint.pickpoint.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val googleFont = GoogleFont(name = "Roboto")

val robotoFontFamily = runCatching{
    FontFamily(
        Font(googleFont = googleFont, fontProvider = provider)
    )
}.getOrDefault(FontFamily.Default)

/*
 * LetterSpacing Rules
 * 32sp and above -> letterSpacing = 0.sp
 * 22~28sp -> letterSpacing = 0.sp
 * 16sp -> letterSpacing = 0.5.sp
 * 14sp and under -> letterSpacing = 0.5.sp ~ 1.sp
 *
 * FontWeight
 * displayLarge ~ headlineLarge -> FontWeight.Bold
 * titleLarge ~ titleSmall -> FontWeight.Medium
 * bodyLarge ~ bodySmall -> FontWeight.Normal
 * labelLarge ~ labelSmall -> FontWeight.Medium
 */
val RobotoTypography = Typography(
    // Countdown Text, Try Again,
    displayLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = 0.sp
    ),
    // Point에 표시되는 text
    displaySmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    // Top App Bar text, Tap To Start,
    titleLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Results Title,
    headlineLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    // Number Setting Component Text,
    headlineMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    // Home Button Text, Setting(Report) Popup Text, Setting Title, Confirm/Reset Button,
    labelLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
    // Setting Content Text,
    labelMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    // Result Content,
    bodyMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    ),
)