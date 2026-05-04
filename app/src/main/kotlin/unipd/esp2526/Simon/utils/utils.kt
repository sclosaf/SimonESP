package unipd.esp2526.Simon.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.graphics.Color

import unipd.esp2526.Simon.ui.theme.ColorType

fun buildSequence(fullSequence: List<ColorType>, errorIndex: Int?) : AnnotatedString
{
    if(fullSequence.isEmpty())
        return AnnotatedString("")

    val separator = errorIndex ?: fullSequence.size

    return buildAnnotatedString{
        fullSequence.forEachIndexed{ index, color ->

            withStyle(style = SpanStyle(color = if(index >= separator) Color.Red else Color.Green))
            {
                append(color.shortName)
                if(index < fullSequence.size - 1)
                    append(", ")
            }
        }
    }
}
