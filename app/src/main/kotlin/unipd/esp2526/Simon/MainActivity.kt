package unipd.esp2526.Simon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

import unipd.esp2526.Simon.ui.GameScreen
import unipd.esp2526.Simon.ui.theme.Theme
import unipd.esp2526.Simon.viewModel.LanguageSwitcher
import unipd.esp2526.Simon.viewModel.GameStatus

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val languageSwitcher: LanguageSwitcher = viewModel()
            val gameStatus: GameStatus = viewModel()

            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    GameScreen(
                        onGameEnd = { sequence ->
                            gameStatus.reset()
                        },
                        languageSwitcher = languageSwitcher,
                        gameStatus = gameStatus

                    )
                }
            }
        }
    }
}
