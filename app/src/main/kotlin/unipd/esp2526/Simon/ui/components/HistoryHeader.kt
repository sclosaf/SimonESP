package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import unipd.esp2526.Simon.ui.theme.*
import unipd.esp2526.Simon.R

/**
 * Header component for the history screen,
 * indicating what each column represents.
 */
@Composable
fun HistoryHeader()
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Text(
                text = stringResource(R.string.number),
                style = MaterialTheme.typography.titleLarge,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = stringResource(R.string.sequence),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(11f)
                    .padding(start = 16.dp)
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp),
            thickness = 2.dp,
            color = horizontalDivider
        )
    }
}
