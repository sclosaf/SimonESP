package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.viewModel.Match
import unipd.esp2526.Simon.ui.theme.ColorType

@Composable
fun HistoryEntry(match : Match)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Text(
            text = (match.errorIndex ?: match.fullSequence.size).toString(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = buildSequence(match.fullSequence, match.errorIndex),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(11f)
                .padding(start = 16.dp)
        )
    }
}

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
