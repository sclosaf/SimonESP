package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.ui.theme.*

/**
 * A grid for the six colored buttons of the game.
 *
 * This composable displays all the buttons arranged
 * in a 3x2 grid layout (3 rows, 2 columns).
 *
 * @param colors List of six ColorType values defining the colors to display
 *               in the grid. Must contain exactly 6 elements for proper layout.
 * @param lit The currently illuminated color, or null if none is lit.
 *            Buttons matching this value will display their lit state.
 * @param onColorClick Callback invoked when a button is pressed.
 */
@Composable
fun ButtonGrid(colors: List<ColorType>, lit: ColorType?, onColorClick: (ColorType) -> Unit)
{
    ElevatedCard(
        modifier = Modifier
            .wrapContentSize()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(8.dp),
    )
    {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
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
}
