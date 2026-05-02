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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.horizontalDivider
import unipd.esp2526.Simon.ui.components.HistoryEntry
import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.ui.components.HistoryHeader
import unipd.esp2526.Simon.ui.components.WelcomeHeader
import unipd.esp2526.Simon.ui.components.NewGameButton
import unipd.esp2526.Simon.viewModel.GameHistory
import unipd.esp2526.Simon.viewModel.LanguageSwitcher

@Composable
fun HomeScreen(
    gameHistory: GameHistory,
    languageSwitcher: LanguageSwitcher,
    onNewGame: () -> Unit
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
                    items(matches)
                    { match ->
                        HistoryEntry(match = match)

                        if(matches.last() !== match)
                        {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = 4.dp),
                                thickness = 0.5.dp,
                                color = horizontalDivider
                            )
                        }
                    }
                }
            }
        }

        NewGameButton(
            onClick = onNewGame,
            modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(20.dp)
        )

    }
}
