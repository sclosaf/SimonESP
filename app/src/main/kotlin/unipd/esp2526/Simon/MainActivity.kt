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
import unipd.esp2526.Simon.ui.HistoryScreen
import unipd.esp2526.Simon.ui.theme.Theme
import unipd.esp2526.Simon.viewModel.LanguageSwitcher
import unipd.esp2526.Simon.viewModel.GameStatus
import unipd.esp2526.Simon.viewModel.GameHistory

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val languageSwitcher: LanguageSwitcher = viewModel()
            val gameStatus: GameStatus = viewModel()
            val gameHistory: GameHistory = viewModel()

            var showHistory by remember { mutableStateOf(false) }

            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    if(showHistory)
                    {
                        HistoryScreen(
                            gameHistory = gameHistory,
                            languageSwitcher = languageSwitcher,
                            onBackPressed = { showHistory = false }
                        )
                    }
                    else
                    {
                        GameScreen(
                        onGameEnd = { sequence ->
                            gameHistory.addSequence(sequence)
                            gameStatus.reset()
                            showHistory = true
                        },
                        languageSwitcher = languageSwitcher,
                        gameStatus = gameStatus
                        )
                    }
                }
            }
        }
    }
}
