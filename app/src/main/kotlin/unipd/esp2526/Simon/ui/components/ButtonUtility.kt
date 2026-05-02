package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import unipd.esp2526.Simon.ui.theme.disabledButton
import unipd.esp2526.Simon.ui.theme.darkStart
import unipd.esp2526.Simon.ui.theme.lightStart
import unipd.esp2526.Simon.ui.theme.darkPause
import unipd.esp2526.Simon.ui.theme.lightPause
import unipd.esp2526.Simon.ui.theme.darkResume
import unipd.esp2526.Simon.ui.theme.lightResume
import unipd.esp2526.Simon.ui.theme.darkEnd
import unipd.esp2526.Simon.ui.theme.lightEnd
import unipd.esp2526.Simon.R

@Composable
fun ButtonUtility(
    onStart: () -> Unit,
    onPauseResume: () -> Unit,
    onEnd: () -> Unit,
    isStartEnabled: Boolean = true,
    isPaused: Boolean = false,
    isEndEnabled: Boolean = false
){

    val startColor = if(isSystemInDarkTheme()) darkStart else lightStart
    val pauseColor = if(isSystemInDarkTheme()) darkPause else lightPause
    val resumeColor = if(isSystemInDarkTheme()) darkResume else lightResume
    val endColor = if(isSystemInDarkTheme()) darkEnd else lightEnd

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp))
    {
        Card(
            modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = isStartEnabled) { onStart() },
            colors = CardDefaults.cardColors(
                containerColor = if(isStartEnabled) startColor else disabledButton
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
                    text = stringResource(R.string.start),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if(isStartEnabled) Color.White else Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Card(
            modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = !isStartEnabled) { onPauseResume() },
            colors = CardDefaults.cardColors(
                containerColor = if(!isStartEnabled) {
                    if(isPaused) resumeColor else pauseColor
                } else {
                    disabledButton
                }
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
                    text = stringResource(if(isPaused) R.string.resume else R.string.pause),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = if(!isStartEnabled) Color.White else Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Card(
            modifier = Modifier
            .weight(1f)
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = isEndEnabled) { onEnd() },
            colors = CardDefaults.cardColors(
                containerColor = if(isEndEnabled) endColor else disabledButton
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
                    text = stringResource(R.string.end),
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = if(isEndEnabled) Color.White else Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
