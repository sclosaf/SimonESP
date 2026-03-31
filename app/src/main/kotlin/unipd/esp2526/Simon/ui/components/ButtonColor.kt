package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import unipd.esp2526.Simon.ui.theme.*

@Composable
fun ButtonColor(color: ColorType, isLit: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier)
{
    val backgroundColor = getColor(color, isLit)
    val elevation = if(isLit) 8.dp else 2.dp

    Card(
        modifier = modifier
        .fillMaxWidth()
        .aspectRatio(4f),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ){
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
        ){}
    }
}

fun getColor(color: ColorType, isLit: Boolean): Color
{
    return when(color)
    {
        ColorType.RED -> if(isLit) RedLit else RedNormal
        ColorType.GREEN -> if(isLit) GreenLit else GreenNormal
        ColorType.BLUE -> if(isLit) BlueLit else BlueNormal
        ColorType.MAGENTA -> if(isLit) MagentaLit else MagentaNormal
        ColorType.YELLOW -> if(isLit) YellowLit else YellowNormal
        ColorType.CYAN -> if(isLit) CyanLit else CyanNormal
    }
}


