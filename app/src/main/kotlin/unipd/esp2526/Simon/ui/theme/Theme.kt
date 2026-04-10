package unipd.esp2526.Simon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Primary color used in dark theme.
 */
val darkPrimary = Color(0xFFD0BCFF)

/**
 * Secondary color used in dark theme.
 */
val darkSecondary = Color(0xFFCCC2DC)

/**
 * Tertiary color used in dark theme.
 */
val darkTertiary = Color(0xFFEFB8C8)

/**
 * Background color used in dark theme.
 */
val darkBackground = Color(0xFF1A1A1A)

/**
 * Surface color used in dark theme.
 */
val darkSurface = Color(0xFF2A2A2A)

/**
 * Primary color used in light theme.
 */
val lightPrimary = Color(0xFF6650a4)

/**
 * Secondary color used in light theme.
 */
val lightSecondary = Color(0xFF625b71)

/**
 * Tertiary color used in light theme.
 */
val lightTertiary = Color(0xFF7D5260)

/**
 * Background color used in light theme.
 */
val lightBackground = Color(0xFFFFFFFF)

/**
 * Surface color used in light theme.
 */
val lightSurface = Color(0xFFF5F5F5)

/**
 * Color used for disabled buttons.
 */
val disabledButton = Color(0xFFBDBDBD)

/**
 * Clear button color used in dark theme.
 */
val darkClear = Color(0xFFEF5350)

/**
 * Clear button color used in light theme.
 */
val lightClear = Color(0xFFE53935)

/**
 * End game button color used in dark theme.
 */
val darkEnd = Color(0xFF66BB6A)

/**
 * End game button color used in light theme.
 */
val lightEnd = Color(0xFF43A047)

/**
 * Back button color used in dark theme.
 */
val darkBack = Color(0xFFEF5350)

/**
 * Back button color used in light theme.
 */
val lightBack = Color(0xFFE53935)

/**
 * Color used for horizontal dividers between history entries.
 */
val horizontalDivider = Color(0xFF9E9E9E).copy(alpha = 0.3f)

/**
 * Dark color scheme following Material Design 3 guidelines.
 */
private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    secondary = darkSecondary,
    tertiary = darkTertiary,
    background = darkBackground,
    surface = darkSurface
)

/**
 * Light color scheme following Material Design 3 guidelines.
 */
private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = lightSecondary,
    tertiary = lightTertiary,
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
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme, typography = Typography(), content = content)
}
