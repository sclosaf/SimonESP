package unipd.esp2526.Simon.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import android.content.res.Configuration.ORIENTATION_LANDSCAPE

import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.ui.components.ButtonBack
import unipd.esp2526.Simon.ui.components.GameScore
import unipd.esp2526.Simon.ui.components.DetailedSequence
import unipd.esp2526.Simon.viewModel.LanguageSwitcher
import unipd.esp2526.Simon.viewModel.Match
import unipd.esp2526.Simon.R

/**
 * Screen that displays a detailed view of a completed match.
 *
 * This composable shows the match error index and the longest
 * sequence correctly reproduced, showing the full color sequence
 * with visual distinction between the correct part and the error part.
 *
 * The layout adapts to both portrait and landscape orientations.
 * The system responds to both the back button provided
 * and the back gesture to navigate to the previous screen.
 *
 * @param languageSwitcher Manages the current language state
 * @param onBack Callback invoked when the user presses the system back button or the custom back button
 * @param match The match data to display, containing the full sequence and error index
 */
@Composable
fun DetailScreen
(
    languageSwitcher: LanguageSwitcher,
    onBack: () -> Unit,
    match: Match
)
{
    val isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE

    BackHandler { onBack() }

    if(isLandscape)
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        )
        {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                TopBar(stringResource(R.string.detail), languageSwitcher = languageSwitcher)

                GameScore(match = match)
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                DetailedSequence(match)

                ButtonBack(onClick = onBack)
            }
        }
    }
    else
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            TopBar(stringResource(R.string.detail), languageSwitcher = languageSwitcher)

            GameScore(match = match)

            Spacer(modifier = Modifier.weight(1f))

            DetailedSequence(match)

            ButtonBack(onClick = onBack)
        }
    }
}
