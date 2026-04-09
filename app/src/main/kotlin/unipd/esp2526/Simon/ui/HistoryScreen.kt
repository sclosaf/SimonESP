package unipd.esp2526.Simon.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.*
import unipd.esp2526.Simon.ui.components.HistoryEntry
import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.ui.components.ButtonBack
import unipd.esp2526.Simon.ui.components.HistoryHeader
import unipd.esp2526.Simon.viewModel.GameHistory
import unipd.esp2526.Simon.viewModel.LanguageSwitcher

@Composable
fun HistoryScreen(
    gameHistory: GameHistory,
    languageSwitcher: LanguageSwitcher,
    back: () -> Unit
)
{
    val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    val matches = gameHistory.endedMatches

    BackHandler { back() }

    if(isLandscape)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
        )
        {
            TopBar(stringResource(R.string.history), languageSwitcher = languageSwitcher)

            Spacer(modifier = Modifier.height(12.dp))

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

            ButtonBack( onClick = back )
        }

    }
    else
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
        )
        {
            TopBar(stringResource(R.string.history), languageSwitcher = languageSwitcher)

            Spacer(modifier = Modifier.height(20.dp))

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

            ButtonBack( onClick = back )
        }
    }
}
