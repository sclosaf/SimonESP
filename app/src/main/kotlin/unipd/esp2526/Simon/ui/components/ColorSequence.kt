package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.*
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
import androidx.compose.material3.ElevatedCard

import unipd.esp2526.Simon.ui.theme.ColorType

@Composable
fun ColorSequence(sequence: List<ColorType>, modifier: Modifier = Modifier)
{
    val textSequence = if(sequence.isEmpty()) {""} else {sequence.joinToString(", "){ it.shortName }}

    ElevatedCard(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
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
