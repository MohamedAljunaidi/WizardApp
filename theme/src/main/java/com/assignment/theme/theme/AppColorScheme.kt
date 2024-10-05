package com.assignment.theme.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color


data class AppColor(
    val white: Color = Color.Unspecified,
    val black: Color = Color.Unspecified,
    val subTitleColor: Color = Color.Unspecified,
    val topAppBar: Color = Color.Unspecified,
    val secondaryBackground: Color = Color.Unspecified,
    val fieldColor: Color = Color.Unspecified,
    val borderColor: Color = Color.Unspecified,
    val selectedColor: Color = Color.Unspecified,
    val unSelectedColor: Color = Color.Unspecified,
)

internal val LocalAppColor = compositionLocalOf { AppColor() }

val MaterialTheme.color: AppColor
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColor.current


internal val LocalLightColorScheme = AppColor(
    white = White,
    black = Black,
    topAppBar = White,
    secondaryBackground = Black95,
    fieldColor = Gray30,
    borderColor = Gray10,
    selectedColor = Black,
    unSelectedColor = Black70,
    subTitleColor = Black40,
)

internal val LocalDarkColorScheme = AppColor(
    white = Black,
    black = White,
    topAppBar = Black10,
    secondaryBackground = Black,
    fieldColor = Black20,
    borderColor = Black,
    selectedColor = Gold,
    unSelectedColor = Black70,
    subTitleColor = Gold,

    )

