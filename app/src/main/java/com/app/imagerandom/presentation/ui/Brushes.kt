package com.app.imagerandom.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

data class Brushes(
    val borderButtonBrush: (Int, Int) -> Brush,  // Giữ nguyên cách sử dụng tham số
    val borderCircleSwitchBrush: Brush,
    val borderBodySwitchBrush: Brush,
    val borderHomeSliderBrush: Brush,
    val borderHomeSliderTrackBrush: Brush,
    val borderSelectedPresetButtonBrush: (Int, Int) -> Brush
)

fun getBrushes(isDarkMode: Boolean): Brushes {
    return if (isDarkMode) {
        Brushes(
            borderButtonBrush = { width, height ->  // Hàm có tham số
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFF73767A).copy(alpha = 0.32f),
                        0.5f to Color(0xFF73767A).copy(alpha = 0.32f),
                        0.8f to Color(0xFF73767A).copy(alpha = 0.28f),
                        1f to Color.Transparent
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(width * 0.025f, width * 0.18f)
                )
            },
            borderCircleSwitchBrush = Brush.linearGradient(
                colors = listOf(Color(0xFF82878C), Color(0xFF363A3E).copy(alpha = 0.7f))
            ),
            borderBodySwitchBrush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF292929).copy(alpha = 0.5f),
                    Color(0xFF4A4E51).copy(alpha = 0.5f)
                )
            ),
            borderHomeSliderBrush = Brush.verticalGradient(
                colors = listOf(Color(0xFF313439), Color(0xFF313439))
            ),
            borderHomeSliderTrackBrush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color(0xFF666666)),
            ),
            borderSelectedPresetButtonBrush = { width, height ->
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFF303236),
                        0.5f to Color(0xFF303236),
                        0.8f to Color(0xFF303236).copy(alpha = 0.6f),
                        1f to Color(0xFF404345)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(width * 0.05f, width * 0.3f)
                )
            }
        )
    } else {
        Brushes(
            borderButtonBrush = { width, height ->  // Hàm có tham số
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.2f to Color.White,
                        1f to Color(0xFFE0E0E0).copy(alpha = 0.4f)
                    )
                )
            },
            borderCircleSwitchBrush = Brush.linearGradient(
                colors = listOf(Color(0xFFE2E2E2).copy(alpha = 0.4f), Color.White)
            ),
            borderBodySwitchBrush = Brush.linearGradient(
                colors = listOf(Color(0xFFD0D0D0).copy(alpha = 0.6f), Color.White),
                start = Offset(1f, 1f),
                end = Offset(0f, 0f)
            ),
            borderHomeSliderBrush = Brush.verticalGradient(
                colorStops = arrayOf(
                    0f to Color(0xFFE2E2E2),
                    0.5f to Color.White,
                    1f to Color.White
                ),
            ),
            borderHomeSliderTrackBrush = Brush.verticalGradient(
                colors = listOf(Color(0xFFB0B1B5), Color(0xFF7C7D80)),
            ),
            borderSelectedPresetButtonBrush = { width, height ->
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0.0f to Color(0xFF73767A).copy(alpha = 0.3f),
                        0.5f to Color(0xFF73767A).copy(alpha = 0.2f),
                        0.8f to Color(0xFF73767A).copy(alpha = 0.1f),
                        1f to Color.Transparent
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(width * 0.04f, width * 0.3f)
                )
            }
        )
    }
}

val LocalBrushes = staticCompositionLocalOf<Brushes> {
    error("No ButtonBorders provided")
}

@Composable
fun ProvideBrushes(content: @Composable () -> Unit) {
    val isDarkMode = isDark()
    val brushes = getBrushes(isDarkMode)

    CompositionLocalProvider(LocalBrushes provides brushes) {
        content()
    }
}