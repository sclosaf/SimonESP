package unipd.esp2526.Simon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val darkPrimary = Color(0xFFD0BCFF)
val darkSecondary  = Color(0xFFCCC2DC)
val darkTertiary = Color(0xFFEFB8C8)
val darkBackground = Color(0xFF1A1A1A)
val darkSurface = Color(0xFF2A2A2A)

val lightPrimary = Color(0xFF6650a4)
val lightSecondary = Color(0xFF625b71)
val lightTertiary = Color(0xFF7D5260)
val lightBackground = Color(0xFFFFFFFF)
val lightSurface = Color(0xFFF5F5F5)

val disabledButton = Color(0xFFBDBDBD)

val darkDelete = Color(0xFFEF5350)
val lightDelete = Color(0xFFE53935)

val darkEnd = Color(0xFF66BB6A)
val lightEnd = Color(0xFF43A047)

val darkBack = Color(0xFFEF5350)
val lightBack = Color(0xFFE53935)

val horizontalDivider = Color(0xFF9E9E9E).copy(alpha = 0.3f)

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    secondary = darkSecondary,
    tertiary = darkTertiary,
    background = darkBackground,
    surface = darkSurface
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    secondary = lightSecondary,
    tertiary = lightTertiary,
    background = lightBackground,
    surface = lightSurface
)

@Composable
fun Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit)
{
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme, typography = Typography(), content = content)
}
