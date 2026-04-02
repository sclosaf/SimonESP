package unipd.esp2526.Simon.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import unipd.esp2526.Simon.ui.components.ButtonGrid
import unipd.esp2526.Simon.ui.components.ColorSequence
import unipd.esp2526.Simon.ui.theme.ColorType
import unipd.esp2526.Simon.viewModel.GameStatus

@Composable
fun GameScreen(onGameEnd: (List<ColorType>) -> Unit, viewModel: GameStatus = viewModel())
{
    val isLandscape = LocalConfiguration.current.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val currentSequence by remember { derivedStateOf { viewModel.currentSequence } }
    val litColor by remember { derivedStateOf { viewModel.litColor } }
    val isGameActive by remember { derivedStateOf { viewModel.isGameActive } }

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
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            ButtonGrid(
                colors = colors,
                lit = litColor,
                onColorClick = { color ->
                    if(isGameActive)
                    {
                        viewModel.addColor(color)
                    }
                },
                modifier = Modifier.weight(1f)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                ColorSequence(
                    sequence = currentSequence,
                    modifier = Modifier.weight(1f)
                )

                // Utility buttons TO DO
                Spacer(modifier = Modifier.weight(0.5f))
            }
        }
    }
    else
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            ButtonGrid(
                colors = colors,
                lit = litColor,
                onColorClick = { color ->
                    if(isGameActive)
                    {
                        viewModel.addColor(color)
                    }
                },
                modifier = Modifier.weight(2f)
            )

            ColorSequence(
                sequence = currentSequence,
                modifier = Modifier.weight(1f)
            )

            // Utility buttons TO DO
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}
