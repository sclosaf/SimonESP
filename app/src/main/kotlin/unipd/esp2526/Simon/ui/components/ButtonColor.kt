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

@Composable
fun ButtonColor(color: ColorType, isLit: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier)
{
    val backgroundColor = getColor(color, isLit)
    val elevation = if (isLit) 8.dp else 4.dp

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2.75f),
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
