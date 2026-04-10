package unipd.esp2526.Simon.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Color used for the normal (unlit) state of the RED button.
 */
val RedNormal = Color(0xFFE53935)

/**
 * Color used for the normal (unlit) state of the GREEN button.
 */
val GreenNormal = Color(0xFF43A047)

/**
 * Color used for the normal (unlit) state of the BLUE button.
 */
val BlueNormal = Color(0xFF0D47A1)

/**
 * Color used for the normal (unlit) state of the MAGENTA button.
 */
val MagentaNormal = Color(0xFFE91E63)

/**
 * Color used for the normal (unlit) state of the YELLOW button.
 */
val YellowNormal = Color(0xFFFDD835)

/**
 * Color used for the normal (unlit) state of the CYAN button.
 */
val CyanNormal = Color(0xFF4DD0E1)

/**
 * Color used for the illuminated (lit) state of the RED button.
 */
val RedLit = Color(0xFFFF6B68)

/**
 * Color used for the illuminated (lit) state of the GREEN button.
 */
val GreenLit = Color(0xFF66BB6A)

/**
 * Color used for the illuminated (lit) state of the BLUE button.
 */
val BlueLit = Color(0xFF1976D2)

/**
 * Color used for the illuminated (lit) state of the MAGENTA button.
 */
val MagentaLit = Color(0xFFEC407A)

/**
 * Color used for the illuminated (lit) state of the YELLOW button.
 */
val YellowLit = Color(0xFFFFEB3B)

/**
 * Color used for the illuminated (lit) state of the CYAN button.
 */
val CyanLit = Color(0xFF80DEEA)

/**
 * Enumeration representing the six possible colors in the game.
 *
 * Each color has a long name and a single-character
 * short name used for sequence display.
 *
 * @property longName The full name of the color
 * @property shortName A abbreviation of the color used for compact sequence representation in the history
 */
enum class ColorType(val longName: String, val shortName: String)
{
    RED("Red", "R"),
    GREEN("Green", "G"),
    BLUE("Blue", "B"),
    MAGENTA("Magenta", "M"),
    YELLOW("Yellow", "Y"),
    CYAN("Cyan", "C")
}
