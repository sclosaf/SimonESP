package unipd.esp2526.Simon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import unipd.esp2526.Simon.ui.GameScreen
import unipd.esp2526.Simon.ui.HomeScreen
import unipd.esp2526.Simon.ui.DetailScreen
import unipd.esp2526.Simon.ui.theme.Theme
import unipd.esp2526.Simon.viewModel.Match
import unipd.esp2526.Simon.viewModel.LanguageSwitcher
import unipd.esp2526.Simon.viewModel.GameStatus
import unipd.esp2526.Simon.viewModel.GameHistory

class MainActivity : AppCompatActivity()
{
    companion object
    {
        private const val KEY_GAME_STATUS= "gameStatus"
    }

    private lateinit var gameStatus: GameStatus

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navigationController = rememberNavController()

            val languageSwitcher: LanguageSwitcher = viewModel()
            val gameHistory: GameHistory = viewModel()

            gameStatus = viewModel()
            savedInstanceState?.getBundle(KEY_GAME_STATUS)?.let { bundle -> gameStatus.restoreState(bundle) }

            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    NavHost(navController = navigationController, startDestination = "HomeScreen")
                    {
                        composable("HomeScreen")
                        {
                            HomeScreen(
                                gameHistory = gameHistory,
                                languageSwitcher = languageSwitcher,
                                onNewGame = { navigationController.navigate("GameScreen") },
                                onMatchClick = { index ->
                                    navigationController.navigate("DetailScreen/${index}")
                                }
                            )
                        }

                        composable("DetailScreen/{index}") { entry ->
                            val index = entry.arguments?.getString("index")?.toIntOrNull() ?: -1

                            val match = if (index in gameHistory.endedMatches.indices)
                                gameHistory.endedMatches[index]
                            else
                                Match(emptyList(), null)

                            DetailScreen(
                                languageSwitcher = languageSwitcher,
                                onBack = {
                                    if(navigationController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED)
                                        navigationController.popBackStack()
                                },
                                match = match
                            )
                        }

                        composable("GameScreen")
                        {
                            GameScreen(
                                onGameEnd = { sequence, errorIndex ->
                                    gameHistory.addSequence(sequence, errorIndex)
                                    gameStatus.resetGame()

                                    if(navigationController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED)
                                        navigationController.popBackStack()
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

    override fun onSaveInstanceState(outState : Bundle)
    {
        super.onSaveInstanceState(outState)

        if(::gameStatus.isInitialized)
        {
            val bundle = Bundle()
            gameStatus.saveState(bundle)
            outState.putBundle(KEY_GAME_STATUS, bundle)
        }
    }
}
