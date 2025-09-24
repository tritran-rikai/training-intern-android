package com.app.imagerandom.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.imagerandom.domain.model.AppTheme
import com.app.imagerandom.presentation.view.customview.DpOffset
private val DarkColorPalette = darkColorScheme(
    primary = backgroundButtonColorDark,
    onPrimary = textButtonColorDark,
    secondary = checkedSwitchColorDark,
    onSecondary = circleSwitchColorDark,
    background = backgroundScreenColorDark,
    onSurface = shadowCircleSwitchColorDark,
)

private val LightColorPalette = lightColorScheme(
    primary = backgroundButtonColor,
    onPrimary = textButtonColor,
    secondary = checkedSwitchColor,
    onSecondary = circleSwitchColor,
    background = backgroundScreenColor,
    onSurface = shadowCircleSwitchColor,
)

val ColorScheme.offsets: List<DpOffset>
    @Composable
    get() = if (isDark()) {
        listOf(
            DpOffset((-6).dp, (-1).dp),
            DpOffset(0.dp, 9.dp),
            DpOffset(2.dp, 2.dp),
            DpOffset(4.dp, 4.dp)
        )
    } else {
        listOf(
            DpOffset(0.dp, 9.dp),
            DpOffset(2.dp, 2.dp),
            DpOffset((-6).dp, (-1).dp),
            DpOffset(4.dp, 4.dp)
        )
    }

val ColorScheme.BottomNavigationMode: Color
    @Composable
    get() = if (isDark()) BottomNavigationDarkModeBg else BottomNavigationLightModeBg

val ColorScheme.BackgroundMode: Color
    @Composable
    get() = if (isDark()) BackgroundDarkMode else BackgroundLightMode

val ColorScheme.Coating: Color
    @Composable
    get() = if (isDark()) coating else Color.White.copy(0f)

val ColorScheme.CoatingIcon: Color
    @Composable
    get() = if (isDark()) coating else coatingIconLight

val ColorScheme.icon: Color
    @Composable
    get() = if (isDark()) iconDark else iconLight

val ColorScheme.iconActive: Color
    @Composable
    get() = if (isDark()) iconDarkActive else iconLightActive

val ColorScheme.selectedTop: Color
    @Composable
    get() = if (isDark()) selectedDark else selected

val ColorScheme.shadow1: Color
    @Composable get() = if (isDark()) shadowDark1 else shadowLight1

val ColorScheme.shadow2: Color
    @Composable get() = if (isDark()) shadowDark2 else shadowLight2

val ColorScheme.shadow3: Color
    @Composable get() = if (isDark()) shadowDark3 else shadowLight3

val ColorScheme.shadow4: Color
    @Composable get() = if (isDark()) shadowDark4 else shadowLight4

@Composable
fun isDark(): Boolean = when (LocalAppTheme.current) {
    AppTheme.DARK -> true
    AppTheme.LIGHT -> false
    AppTheme.SYSTEM -> isSystemInDarkTheme()
}

val LocalAppTheme = staticCompositionLocalOf { AppTheme.SYSTEM }

@Composable
fun MyApplicationTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val isDark = when (appTheme) {
        AppTheme.LIGHT -> false
        AppTheme.DARK -> true
        AppTheme.SYSTEM -> isSystemInDarkTheme()
    }

    val colors = if (isDark) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(LocalAppTheme provides appTheme) {
        MaterialTheme(
            colorScheme = colors,
            Shapes,
            Typography,
            content = content
        )
    }
}