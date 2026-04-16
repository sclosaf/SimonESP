package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.ElevatedCard

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.ColorType

/**
 * A component that displays the current color sequence as text,
 * as a comma separated list of abbreviations.
 *
 * @param sequence The list of ColorType representing the current game sequence.
 *                 If empty, a placeholder text is displayed to guide the user.
 */
@Composable
fun ColorSequence(sequence: List<ColorType>)
{
    val textSequence = if(sequence.isEmpty()) {stringResource(R.string.press)} else {sequence.joinToString(", "){ it.shortName }}

    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .aspectRatio(1.6f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.elevatedCardColors( containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp)
    )
    {
        Text(
            text = textSequence,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        )
    }
}
