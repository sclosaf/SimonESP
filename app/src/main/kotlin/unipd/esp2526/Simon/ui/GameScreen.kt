package unipd.esp2526.Simon.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.ui.components.ButtonGrid
import unipd.esp2526.Simon.ui.components.ColorSequence
import unipd.esp2526.Simon.ui.components.ButtonUtility
import unipd.esp2526.Simon.ui.components.TopBar
import unipd.esp2526.Simon.ui.theme.ColorType
import unipd.esp2526.Simon.viewModel.GameStatus
import unipd.esp2526.Simon.viewModel.LanguageSwitcher

@Composable
fun GameScreen(
    onGameEnd: (List<ColorType>) -> Unit,
    languageSwitcher: LanguageSwitcher,
    gameStatus: GameStatus
)
{
    val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val currentSequence by remember { derivedStateOf { gameStatus.currentSequence } }
    val litColor by remember { derivedStateOf { gameStatus.litColor } }
    val isGameActive by remember { derivedStateOf { gameStatus.isGameActive } }

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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {

                Spacer(modifier = Modifier.width(20.dp))

                TopBar(languageSwitcher = languageSwitcher)

                Spacer(modifier = Modifier.width(16.dp))

                ButtonGrid(
                    colors = colors,
                    lit = litColor,
                    onColorClick = { color ->
                        if(isGameActive)
                        {
                            gameStatus.addColor(color)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                ColorSequence(
                    sequence = currentSequence,
                    modifier = Modifier.weight(1f)
                )

                ButtonUtility(
                    onDelete = { gameStatus.clearSequence() },
                    onEnd = { onGameEnd(gameStatus.endGame()) },
                    isDeleteEnabled = currentSequence.isNotEmpty(),
                )

                Spacer(modifier = Modifier.width(20.dp))

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
            TopBar(languageSwitcher = languageSwitcher)

            Spacer(modifier = Modifier.width(24.dp))

            ButtonGrid(
                colors = colors,
                lit = litColor,
                onColorClick = { color ->
                    if(isGameActive)
                    {
                        gameStatus.addColor(color)
                    }
                },
                modifier = Modifier.weight(2f)
            )

            ColorSequence(
                sequence = currentSequence,
                modifier = Modifier.weight(1f)
            )

            ButtonUtility(
                onDelete = { gameStatus.clearSequence() },
                onEnd = { onGameEnd(gameStatus.endGame()) },
                isDeleteEnabled = currentSequence.isNotEmpty(),
            )

            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
