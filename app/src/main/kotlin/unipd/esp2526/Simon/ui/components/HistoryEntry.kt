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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import unipd.esp2526.Simon.viewModel.Match

/**
 * A component that displays a single history entry.
 *
 * This composable shows the information about a completed match,
 * displaying the length of the sequence and the sequence itself.
 *
 * @param match The Match object containing the sequence
 *              string and its size to be displayed in the history entry.
 */
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
            text = "${match.size}",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = match.sequence,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(11f)
                .padding(start = 16.dp)
        )
    }
}
