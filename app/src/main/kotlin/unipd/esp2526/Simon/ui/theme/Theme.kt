package unipd.esp2526.Simon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Primary color used in dark theme.
 */
val darkPrimary = Color(0xFF3B82F6)

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
val lightPrimary = Color(0xFF2563EB)

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
 * Color for the start button in dark theme.
 */
val darkStart = Color(0xFF0D7F5B)

/**
 * Color for the start button in light theme.
 */
val lightStart = Color(0xFF059669)

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
