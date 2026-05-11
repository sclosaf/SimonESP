package unipd.esp2526.Simon.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.divider
import unipd.esp2526.Simon.ui.components.HistoryEntry
import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.ui.components.HistoryHeader
import unipd.esp2526.Simon.ui.components.WelcomeHeader
import unipd.esp2526.Simon.ui.components.NewGameButton
import unipd.esp2526.Simon.ui.components.ClearHistoryButton
import unipd.esp2526.Simon.viewModel.GameHistory
import unipd.esp2526.Simon.viewModel.LanguageSwitcher

/**
 * Home screen which displays a welcome message or the list of completed matches.
 *
 * This composable is the main entry point of the application:
 * - Clicking on a match entry, it navigates to the detail screen for that match.
 * - Clicking the newMatch button, it navigates to the game screen to start a new one.
 * - Clicking the clearHistory button, all displayed entries are deleted.
 *
 * @param gameHistory Contains the list of completed matches to display
 * @param languageSwitcher Manages the current language state
 * @param onMatchClick Callback invoked when a match entry is clicked
 * @param onNewGame Callback invoked when the New Game button is pressed
 * @param onClearHistory Callback invoked when the Clear History button is pressed
 */
@Composable
fun HomeScreen(
    gameHistory: GameHistory,
    languageSwitcher: LanguageSwitcher,
    onMatchClick: (Int) -> Unit,
    onNewGame: () -> Unit,
    onClearHistory: () -> Unit
)
{
    val matches = gameHistory.endedMatches

    Box(
        modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.statusBars)
    )
    {
        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TopBar(stringResource(R.string.home), languageSwitcher = languageSwitcher)

            Spacer(modifier = Modifier.height(12.dp))

            if(matches.isEmpty())
            {
                WelcomeHeader()
            }
            else
            {
                HistoryHeader()

                LazyColumn(
                    modifier = Modifier.weight(1f)
                )
                {
                    itemsIndexed(matches) { index, match ->
                        HistoryEntry(
                            match = match,
                            onClick = { onMatchClick(index) }
                        )

                        if(matches.last() !== match)
                        {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                thickness = 0.5.dp,
                                color = divider
                            )
                        }
                    }
                }
            }
        }

        if(matches.isNotEmpty())
        {
            ClearHistoryButton(
                onClick = onClearHistory,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 90.dp, end = 20.dp)
            )
        }

        NewGameButton(
            onClick = onNewGame,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        )
    }
}
