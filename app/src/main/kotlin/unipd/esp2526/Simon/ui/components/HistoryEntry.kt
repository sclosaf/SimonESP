package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.utils.buildSequence
import unipd.esp2526.Simon.viewModel.Match
import unipd.esp2526.Simon.ui.theme.ColorType

@Composable
fun HistoryEntry(
    match : Match,
    onClick: () -> Unit
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick()},
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
            text = buildSequence(match.fullSequence, match.errorIndex, isSystemInDarkTheme()),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(11f)
                .padding(start = 16.dp)
        )
    }
}
