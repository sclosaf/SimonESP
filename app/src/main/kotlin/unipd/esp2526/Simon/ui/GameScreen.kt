package unipd.esp2526.Simon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource

import unipd.esp2526.Simon.ui.components.ButtonGrid
import unipd.esp2526.Simon.ui.components.ColorSequence
import unipd.esp2526.Simon.ui.components.ButtonUtility
import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.ui.theme.ColorType
import unipd.esp2526.Simon.viewModel.GameStatus
import unipd.esp2526.Simon.viewModel.LanguageSwitcher
import unipd.esp2526.Simon.viewModel.GamePhase
import unipd.esp2526.Simon.R

@Composable
fun GameScreen(
    onGameEnd: (fullSequence: List<ColorType>, errorIndex: Int?) -> Unit,
    languageSwitcher: LanguageSwitcher,
    gameStatus: GameStatus
)
{
    val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val currentPhase by remember { derivedStateOf { gameStatus.currentPhase } }
    val playedSequence by remember { derivedStateOf { gameStatus.playedSequence } }

    val litColor by remember { derivedStateOf { gameStatus.litColor } }
    val isPaused by remember { derivedStateOf { gameStatus.isPaused } }

    val isStartEnabled = currentPhase == GamePhase.IDLE
    val isContinueEnabled = currentPhase == GamePhase.CONTINUE
    val isPauseEnabled = currentPhase == GamePhase.COMPUTER
    val isEndEnabled = currentPhase != GamePhase.IDLE

    var lastResult by remember { mutableStateOf<Pair<List<ColorType>, Int?>?>(null) }

    val colors = listOf(
        ColorType.RED,
        ColorType.GREEN,
        ColorType.BLUE,
        ColorType.MAGENTA,
        ColorType.YELLOW,
        ColorType.CYAN
    )

    fun clickColor(color: ColorType)
    {
        val result = gameStatus.colorPressed(color)
        if(result != null)
            lastResult = result
    }

    fun clickEnd()
    {
        val result = gameStatus.forceEndGame()
        if(result != null)
        {
            onGameEnd(result.first, result.second)
            lastResult = null
        }
        else if(lastResult != null)
        {
            lastResult?.let { (fullSequence, errorIndex) ->
                onGameEnd(fullSequence, errorIndex)
                lastResult = null
            }
        }
    }

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
                TopBar(stringResource(R.string.name), languageSwitcher = languageSwitcher)

                ButtonGrid(
                    colors = colors,
                    lit = litColor,
                    onColorClick = { color -> clickColor(color) }
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                ColorSequence(sequence = playedSequence.joinToString(", ") { it.shortName }, phase = currentPhase)

                ButtonUtility(
                    onStart = { gameStatus.startGame() },
                    onContinue = { gameStatus.continueToNextRound() },
                    onPauseResume = { gameStatus.togglePause() },
                    onEnd = { clickEnd() },
                    isStartEnabled = isStartEnabled,
                    isContinueEnabled = isContinueEnabled,
                    isPaused = isPaused,
                    isPauseEnabled = isPauseEnabled,
                    isEndEnabled = isEndEnabled
                )
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
            TopBar(stringResource(R.string.name), languageSwitcher = languageSwitcher)

            ButtonGrid(
                colors = colors,
                lit = litColor,
                onColorClick = { color -> clickColor(color) }
            )

            Spacer(modifier = Modifier.weight(1f))

            ColorSequence(sequence = playedSequence.joinToString(", ") { it.shortName }, phase = currentPhase)

            ButtonUtility(
                onStart = { gameStatus.startGame() },
                onContinue = { gameStatus.continueToNextRound() },
                onPauseResume = { gameStatus.togglePause() },
                onEnd = { clickEnd() },
                isStartEnabled = isStartEnabled,
                isContinueEnabled = isContinueEnabled,
                isPaused = isPaused,
                isPauseEnabled = isPauseEnabled,
                isEndEnabled = isEndEnabled
            )
        }
    }
}
