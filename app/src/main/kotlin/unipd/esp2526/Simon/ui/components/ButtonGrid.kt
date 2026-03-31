package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.ui.theme.*

@Composable
fun ButtonGrid(colors: List<ColorType>, lit: ColorType?, onColorClick: (ColorType) -> Unit, modifier: Modifier = Modifier)
{
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp))
    {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp))
        {
            ButtonColor(
                color = colors[0],
                isLit = lit == colors[0],
                onClick = { onColorClick(colors[0]) },
                modifier = Modifier.weight(1f)
            )

            ButtonColor(
                color = colors[1],
                isLit = lit == colors[1],
                onClick = { onColorClick(colors[1]) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp))
        {
            ButtonColor(
                color = colors[2],
                isLit = lit == colors[2],
                onClick = { onColorClick(colors[2]) },
                modifier = Modifier.weight(1f)
            )

            ButtonColor(
                color = colors[3],
                isLit = lit == colors[3],
                onClick = { onColorClick(colors[3]) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp))
        {
            ButtonColor(
                color = colors[4],
                isLit = lit == colors[4],
                onClick = { onColorClick(colors[4]) },
                modifier = Modifier.weight(1f)
            )

            ButtonColor(
                color = colors[5],
                isLit = lit == colors[5],
                onClick = { onColorClick(colors[5]) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
