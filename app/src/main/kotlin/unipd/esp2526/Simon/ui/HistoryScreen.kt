package unipd.esp2526.Simon.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.ui.components.HistoryEntry
import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.viewModel.GameHistory
import unipd.esp2526.Simon.viewModel.LanguageSwitcher

@Composable
fun HistoryScreen(
    gameHistory: GameHistory,
    languageSwitcher: LanguageSwitcher,
    onBackPressed: () -> Unit
)
{
    val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    val matches = gameHistory.endedMatches

    BackHandler { onBackPressed() }

    if(isLandscape)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            TopBar(languageSwitcher = languageSwitcher)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            )
            {
                items(matches) { match -> HistoryEntry(match = match) }
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            TopBar(languageSwitcher = languageSwitcher)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            )
            {
                items(matches) { match -> HistoryEntry(match = match) }
            }
        }
    }
}
