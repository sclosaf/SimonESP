package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline

import unipd.esp2526.Simon.ui.theme.darkClear
import unipd.esp2526.Simon.ui.theme.lightClear

/**
 * Button used to clear the entire match history.
 *
 * @param modifier Modifier to be applied to the button to customize the layout
 * @param onClick Callback invoked when the button is pressed to clear match history
 */
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
            imageVector = Icons.Outlined.DeleteOutline,
            contentDescription = null
        )
    }
}
