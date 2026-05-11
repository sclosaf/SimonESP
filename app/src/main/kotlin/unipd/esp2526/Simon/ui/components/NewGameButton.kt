package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import unipd.esp2526.Simon.ui.theme.darkNewGame
import unipd.esp2526.Simon.ui.theme.lightNewGame

/**
 * Button used to start a new game.
 *
 * @param modifier Modifier to be applied to the button to customize the layout
 * @param onClick Callback invoked when the button is pressed to start a new game
 */
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
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null
        )
    }
}
