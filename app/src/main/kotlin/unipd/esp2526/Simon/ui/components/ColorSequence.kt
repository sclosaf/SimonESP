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
import unipd.esp2526.Simon.viewModel.GamePhase

/**
 * Displayes text feedback during a game, whether it is a game
 * status message or the sequence played based on the game phase.
 *
 * The content adapts dynamically to the current GamePhase:
 * - GamePhase.IDLE: Shows a starting message
 * - GamePhase.COMPUTER: Shows a waiting message while the computer plays
 * - GamePhase.CONTINUE: Asks the user to choose between advancing or ending
 * - GamePhase.PLAYER: Shows the player's pressed sequence so far
 * - GamePhase.OVER: Shows a conclusion message
 *
 * @param sequence The current sequence of colors
 * @param phase The current game phase determining which message to display
 */
@Composable
fun ColorSequence(sequence: String, phase: GamePhase)
{
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .aspectRatio(1.6f),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp)
    )
    {
        Text(
            text = when(phase)
            {
                GamePhase.IDLE -> stringResource(R.string.press, stringResource(R.string.start))
                GamePhase.COMPUTER -> { stringResource(R.string.wait) }
                GamePhase.CONTINUE -> { stringResource(R.string.choice, stringResource(R.string.advance), stringResource(R.string.end)) }
                GamePhase.PLAYER -> if(sequence.isEmpty()) stringResource(R.string.play) else sequence
                GamePhase.OVER -> stringResource(R.string.concluded, stringResource(R.string.end))
                else -> sequence
            },
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(12.dp)
        )
    }
}
