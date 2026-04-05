package unipd.esp2526.Simon.ui.theme

import androidx.compose.ui.graphics.Color

val RedNormal = Color(0xFFE53935)
val GreenNormal = Color(0xFF43A047)
val BlueNormal = Color(0xFF0D47A1)
val MagentaNormal = Color(0xFFE91E63)
val YellowNormal = Color(0xFFFDD835)
val CyanNormal = Color(0xFF4DD0E1)

val RedLit = Color(0xFFFF6B68)
val GreenLit = Color(0xFF66BB6A)
val BlueLit = Color(0xFF1976D2)
val MagentaLit = Color(0xFFEC407A)
val YellowLit = Color(0xFFFFEB3B)
val CyanLit = Color(0xFF80DEEA)

enum class ColorType(val longName: String, val shortName: String)
{
    RED("Red", "R"),
    GREEN("Green", "G"),
    BLUE("Blue", "B"),
    MAGENTA("Magenta", "M"),
    YELLOW("Yellow", "Y"),
    CYAN("Cyan", "C")
}
