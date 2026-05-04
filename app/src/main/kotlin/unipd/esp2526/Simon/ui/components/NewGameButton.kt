package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color

import unipd.esp2526.Simon.ui.theme.darkNewGame
import unipd.esp2526.Simon.ui.theme.lightNewGame

@Composable
fun NewGameButton(modifier: Modifier = Modifier, onClick: () -> Unit)
{
    val buttonColor = if(isSystemInDarkTheme()) darkNewGame else lightNewGame

    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        containerColor = buttonColor,
        contentColor = Color.White
    )
    {
        Text(
            text = "+",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
