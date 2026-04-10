package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import unipd.esp2526.Simon.ui.theme.*

/**
 * A single colored, illuminable button.
 *
 * This composable is used to create the buttons needed for the Simon game.
 * Each button has two visual states:
 * - **Normal**: Standard color when not pressed
 * - **Lit**: Brighter color providing visual feedback when pressed
 *
 * @param color The type of color this button represents
 * @param isLit Whether the button should be displayed in its lit (illuminated) state.
 * @param onClick Callback invoked when the button is pressed by the user.
 *                Should add the corresponding color to the current sequence.
 * @param modifier Modifier to be applied to the button for layout customization.
 */
@Composable
fun ButtonColor(color: ColorType, isLit: Boolean, onClick: () -> Unit, modifier: Modifier)
{
    val backgroundColor = getColor(color, isLit)
    val elevation = if (isLit) 8.dp else 4.dp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2.5f),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        shape = RoundedCornerShape(12.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                {
                    onClick()
                }
        )
    }
}

/**
 * Returns the appropriate color for a button based on its type and illumination state.
 *
 * @param color The type of color button
 * @param isLit Whether the button is currently illuminated
 *
 * @return The corresponding Color value for the button's current state
 */
fun getColor(color: ColorType, isLit: Boolean): Color
{
    return when (color)
    {
        ColorType.RED -> if (isLit) RedLit else RedNormal
        ColorType.GREEN -> if (isLit) GreenLit else GreenNormal
        ColorType.BLUE -> if (isLit) BlueLit else BlueNormal
        ColorType.MAGENTA -> if (isLit) MagentaLit else MagentaNormal
        ColorType.YELLOW -> if (isLit) YellowLit else YellowNormal
        ColorType.CYAN -> if (isLit) CyanLit else CyanNormal
    }
}
