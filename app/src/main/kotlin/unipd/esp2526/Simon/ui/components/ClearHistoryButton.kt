package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline

import unipd.esp2526.Simon.ui.theme.darkClear
import unipd.esp2526.Simon.ui.theme.lightClear

@Composable
fun ClearHistoryButton(modifier: Modifier = Modifier, onClick: () -> Unit)
{
    val buttonColor = if(isSystemInDarkTheme()) darkClear else lightClear

    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        containerColor = buttonColor,
        contentColor = Color.White
    )
    {
        Icon(
            imageVector = Icons.Default.DeleteOutline,
            contentDescription = null
        )
    }
}
