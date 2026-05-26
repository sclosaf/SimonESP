package unipd.esp2526.Simon.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.graphics.Color

import unipd.esp2526.Simon.ui.theme.darkRedText
import unipd.esp2526.Simon.ui.theme.lightRedText
import unipd.esp2526.Simon.ui.theme.darkGreenText
import unipd.esp2526.Simon.ui.theme.lightGreenText
import unipd.esp2526.Simon.ui.theme.ColorType

/**
 * Utility function that builds an annotated string with visual highlighting
 * of the correct and error parts, based on the indexes provided.
 *
 * The sequence is formatted as comma-separated color short names (e.g., "R, G, B").
 *
 * @param fullSequence The complete list of colors in the match sequence
 * @param errorIndex The index (0-based) where the first error occurred
 * @param isDarkTheme Whether the current theme is dark mode, affecting the specific color used
 * @return An AnnotatedString with styled spans for colored text rendering
 */
fun buildSequence(fullSequence: List<ColorType>, errorIndex: Int?, isDarkTheme: Boolean) : AnnotatedString
{
    val errorColor = if(isDarkTheme) darkRedText else lightRedText
    val correctColor = if(isDarkTheme) darkGreenText else lightGreenText

    if(fullSequence.isEmpty())
        return AnnotatedString("")

    val separator = errorIndex ?: fullSequence.size

    return buildAnnotatedString{
        fullSequence.forEachIndexed{ index, color ->

            withStyle(style = SpanStyle(color = if(index >= separator) errorColor else correctColor))
            {
                append(color.shortName)
                if(index < fullSequence.size - 1)
                    append(", ")
            }
        }
    }
}
