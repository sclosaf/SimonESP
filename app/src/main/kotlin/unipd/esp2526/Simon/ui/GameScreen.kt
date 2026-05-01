package unipd.esp2526.Simon.ui

import android.util.Log
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
import unipd.esp2526.Simon.R

/**
 * Main screen of the game.
 *
 * This composable function defines the whole first screen and displays
 * the interface where the user can play.
 * It properly adapts to the device orientation.
 *
 * The layout in both the orientations shows a title bar, the six game buttons,
 * a text box containing the current sequence played and two utility buttons
 * which can be used to manage the current match.
 *
 * @param onGameEnd Callback invoked when the user ends the game,
 *                  receives the final sequence as a parameter
 * @param languageSwitcher ViewModel for managing language toggle
 * @param gameStatus ViewModel containing the current sequence
 */
@Composable
fun GameScreen(
    onGameEnd: (List<ColorType>) -> Unit,
    languageSwitcher: LanguageSwitcher,
    gameStatus: GameStatus
)
{
    val TAG = "GameScreen"

    val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val currentSequence by remember { derivedStateOf { gameStatus.currentSequence } }
    val litColor by remember { derivedStateOf { gameStatus.litColor } }

    val colors = listOf(
        ColorType.RED,
        ColorType.GREEN,
        ColorType.BLUE,
        ColorType.MAGENTA,
        ColorType.YELLOW,
        ColorType.CYAN
    )

    if(isLandscape)
    {
        Log.d(TAG, "Orientation setted to landscape")

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
                    onColorClick = { color -> gameStatus.addColor(color) }
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                ColorSequence(sequence = currentSequence)

                ButtonUtility(
                    onStart = {},
                    onPauseResume = {},
                    onEnd = { onGameEnd(gameStatus.endGame()) },
                    isStartEnabled = false,
                    isPaused = false,
                    isEndEnabled = currentSequence.isNotEmpty()
                )
            }
        }
    }
    else
    {
        Log.d(TAG, "Orientation setted to portrait")

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
                onColorClick = { color -> gameStatus.addColor(color) }
            )

            Spacer(modifier = Modifier.weight(1f))

            ColorSequence(sequence = currentSequence)

            ButtonUtility(
                onStart = {},
                onPauseResume = {},
                onEnd = { onGameEnd(gameStatus.endGame()) },
                isStartEnabled = false,
                isPaused = false,
                isEndEnabled = currentSequence.isNotEmpty()
            )
        }
    }
}
