package unipd.esp2526.Simon.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.activity.ComponentActivity

import unipd.esp2526.Simon.R
import unipd.esp2526.Simon.viewModel.LanguageSwitcher

/**
 * Top bar with title and language toggle button.
 * It displays a title and a button icon to toggle between
 * the supported languages (English and Italian).
 *
 * @param title The text to display as the bar title
 * @param languageSwitcher ViewModel that manages the current language state
 *                         and provides the toggleLanguage method
 */
@Composable
fun TopBar(title: String, languageSwitcher: LanguageSwitcher)
{
    val context = LocalContext.current
    val currentLanguage = languageSwitcher.currentLanguage

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(64.dp),
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            IconButton(
                onClick = {
                    languageSwitcher.toggleLanguage(context)
                    (context as? ComponentActivity)?.recreate()
                },
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 16.dp)
                    .clip(CircleShape)
            )
            {
                Image(
                    painter = painterResource(id = if(currentLanguage == "it") R.drawable.it else R.drawable.en),
                    contentDescription = "Change Language",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
