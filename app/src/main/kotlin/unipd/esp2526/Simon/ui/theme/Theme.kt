package unipd.esp2526.Simon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Background color used in dark theme.
 */
val darkBackground = Color(0xFF1A1A1A)

/**
 * Surface color used in dark theme.
 */
val darkSurface = Color(0xFF2A2A2A)

/**
 * Background color used in light theme.
 */
val lightBackground = Color(0xFFFFFFFF)

/**
 * Surface color used in light theme.
 */
val lightSurface = Color(0xFFF5F5F5)

/**
 * Color used for new game button in dark theme.
 */
val darkNewGame = Color(0xFF3B82F6)

/**
 * Color used for new game button in light theme.
 */
val lightNewGame = Color(0xFF2563EB)

/**
 * Clear button color used in dark theme.
 */
val darkClear = Color(0xFFEF5350)

/**
 * Clear button color used in light theme.
 */
val lightClear = Color(0xFFE53935)

/**
 * Color used for disabled buttons.
 */
val disabledButton = Color(0xFFBDBDBD)

/**
 * Back button color used in dark theme.
 */
val darkBack = Color(0xFFEF5350)

/**
 * Back button color used in light theme.
 */
val lightBack = Color(0xFFE53935)

/**
 * Color for the start button in dark theme.
 */
val darkStart = Color(0xFF0D7F5B)

/**
 * Color for the start button in light theme.
 */
val lightStart = Color(0xFF059669)

/**
 * Color for the continue button in dark theme.
 */
val darkContinue = Color(0xFF0D7F5B)

/**
 * Color for the continue button in the light theme.
 */
val lightContinue = Color(0xFF059669)

/**
 * Color for the pause button in dark theme.
 */
val darkPause = Color(0xFFEF5350)

/**
 * Color for the pause button in light theme.
 */
val lightPause = Color(0xFFE53935)

/**
 * Color for the resume button in dark theme.
 */
val darkResume = Color(0xFF66BB6A)

/**
 * Color for the resume button in light theme.
 */
val lightResume = Color(0xFF43A047)

/**
 * Color for the end button in dark theme.
 */
val darkEnd = Color(0xFF3B82F6)

/**
 * Color for the end button in light theme.
 */
val lightEnd = Color(0xFF2563EB)

/**
 * Red color for text in dark theme.
 */
val darkRedText = Color(0xFFFF6B6B)

/**
 * Red color for text in light theme.
 */
val lightRedText = Color(0xFFD32F2F)

/**
 * Green color for text in dark theme.
 */
val darkGreenText = Color(0xFF81FF81)

/**
 * Green color for text in light theme.
 */
val lightGreenText = Color(0xFF00C853)

/**
 * Color used for dividers.
 */
val divider = Color(0xFF9E9E9E).copy(alpha = 0.3f)

/**
 * Dark color scheme.
 */
private val DarkColorScheme = darkColorScheme(
    background = darkBackground,
    surface = darkSurface
)

/**
 * Light color scheme.
 */
private val LightColorScheme = lightColorScheme(
    background = lightBackground,
    surface = lightSurface
)

/**
 * Main theme wrapper for the application.
 *
 * ## Theme
 * - **Dark theme**: Automatically activated when the system is in dark mode
 * - **Light theme**: Automatically activated when the system is in light mode
 *
 * @param darkTheme Boolean indicating whether to force dark theme.
 *                  By default follows the system setting.
 * @param content The composable content to be wrapped with the theme.
 */
@Composable
fun Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme, content = content)
}
