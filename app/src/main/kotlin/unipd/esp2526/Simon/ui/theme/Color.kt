package unipd.esp2526.Simon.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Color used for the normal (unlit) state of the RED button.
 */
val RedNormal = Color(0xFFB71C1C)

/**
 * Color used for the normal (unlit) state of the GREEN button.
 */
val GreenNormal = Color(0xFF2E7D32)

/**
 * Color used for the normal (unlit) state of the BLUE button.
 */
val BlueNormal = Color(0xFF0D47A1)

/**
 * Color used for the normal (unlit) state of the MAGENTA button.
 */
val MagentaNormal = Color(0xFFAD1457)

/**
 * Color used for the normal (unlit) state of the YELLOW button.
 */
val YellowNormal = Color(0xFFF9A825)

/**
 * Color used for the normal (unlit) state of the CYAN button.
 */
val CyanNormal = Color(0xFF00838F)

/**
 * Color used for the illuminated (lit) state of the RED button.
 */
val RedLit = Color(0xFFFF5252)

/**
 * Color used for the illuminated (lit) state of the GREEN button.
 */
val GreenLit = Color(0xFF69F0AE)

/**
 * Color used for the illuminated (lit) state of the BLUE button.
 */
val BlueLit = Color(0xFF448AFF)

/**
 * Color used for the illuminated (lit) state of the MAGENTA button.
 */
val MagentaLit = Color(0xFFFF4081)

/**
 * Color used for the illuminated (lit) state of the YELLOW button.
 */
val YellowLit = Color(0xFFFFFF00)

/**
 * Color used for the illuminated (lit) state of the CYAN button.
 */
val CyanLit = Color(0xFF18FFFF)

/**
 * Enumeration representing the six possible colors in the game.
 *
 * Each color has a long name and a single-character
 * short name used for sequence display.
 *
 * @property longName The full name of the color
 * @property shortName A abbreviation of the color used for
 *                     compact sequence representation in the history
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
