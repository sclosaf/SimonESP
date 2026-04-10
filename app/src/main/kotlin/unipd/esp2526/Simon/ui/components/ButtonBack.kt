package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.ui.theme.darkBack
import unipd.esp2526.Simon.ui.theme.lightBack

/**
 * Back button component.
 *
 * This composable is designed to allow the user
 * to navigate back to the previous screen.
 *
 * @param onClick Callback invoked when the button is pressed by the user.
 */
@Composable
fun ButtonBack(onClick : () -> Unit)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable() { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) darkBack else lightBack
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = stringResource(R.string.back),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
