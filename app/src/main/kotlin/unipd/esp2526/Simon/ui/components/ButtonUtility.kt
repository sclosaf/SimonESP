package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import unipd.esp2526.Simon.ui.theme.*
import unipd.esp2526.Simon.R

/**
 * A couple of utility buttons, arranged in a row:
 * - **Clear Button**: Clears the current sequence (disabled when the sequence is empty)
 * - **End Button**: Ends the current game and saves the sequence
 *
 * @param onClear Callback invoked when the Clear button is pressed.
 * @param onEnd Callback invoked when the End button is pressed.
 * @param isClearEnabled Whether the Clear button is enabled. When false, the button
 *                       appears gray and does not respond to clicks. Default is true.
 */
@Composable
fun ButtonUtility(onClear: () -> Unit, onEnd: () -> Unit, isClearEnabled: Boolean = true)
{
    val clearColor = if (isSystemInDarkTheme()) darkClear else lightClear
    val endColor = if (isSystemInDarkTheme()) darkEnd else lightEnd

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp))
    {
        Card(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable() { onClear() },
            colors = CardDefaults.cardColors(
                containerColor = if (isClearEnabled) clearColor else disabledButton
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp)
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = stringResource(R.string.clear),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Card(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable() { onEnd() },
            colors = CardDefaults.cardColors(containerColor = endColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp)
        )
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    text = stringResource(R.string.end),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
