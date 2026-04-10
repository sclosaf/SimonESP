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
import androidx.lifecycle.Lifecycle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import unipd.esp2526.Simon.ui.GameScreen
import unipd.esp2526.Simon.ui.HistoryScreen
import unipd.esp2526.Simon.ui.theme.Theme
import unipd.esp2526.Simon.viewModel.LanguageSwitcher
import unipd.esp2526.Simon.viewModel.GameStatus
import unipd.esp2526.Simon.viewModel.GameHistory

/**
 * Main (and only) activity used for the 'Simon' application.
 *
 * This activity manages the user interface of the application,
 * which implements the navigation among the different screens (Game and Match History)
 * initializes the needed ViewModels for the state management.
 *
 * Jetpack Compose is used for the general interface and navigation.
 *
 * ## ViewModels
 * - GameStatus: manages the current sequence and the light effect of the game buttons
 * - LanguageSwitcher: manages the language toggle
 * - GameHistory: stores the history of the played matches
 *
 * ## Application flow
 * 1. The game starts on the GameScreen
 * 2. Once the user ends the match, the sequence played is saved
 * 3. The user is brought to the HistoryScreen, where all the previous matches are displayed
 * 4. With the Android back button the user can play a new game
 */
class MainActivity : AppCompatActivity()
{
    /**
     * Overridden method called on creation.
     *
     * Enables edge-to-edge display.
     * Initializes the ViewModels to manage variables lifecycles.
     * Sets the application theme, support for both light and dark.
     * Initializes the navigation controller.
     *
     * ## Note
     * The popBackStack method is called only when the current back stack entry
     * is in the Lifecycle.State.RESUMED state to prevent invalid navigation
     * during state transitions.
     *
     * @param savedInstanceState Saved state of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val navigationController = rememberNavController()

            val languageSwitcher: LanguageSwitcher = viewModel()
            val gameStatus: GameStatus = viewModel()
            val gameHistory: GameHistory = viewModel()

            Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    NavHost(navController = navigationController, startDestination = "GameScreen")
                    {
                        /**
                         * Main screen of the application.
                         */
                        composable("GameScreen")
                        {
                            GameScreen(
                                onGameEnd = { sequence ->
                                    gameHistory.addSequence(sequence)
                                    gameStatus.reset()
                                    navigationController.navigate("HistoryScreen")
                                },
                                languageSwitcher = languageSwitcher,
                                gameStatus = gameStatus
                            )
                        }

                        /**
                         * History screen of the application.
                         */
                        composable("HistoryScreen")
                        {
                            HistoryScreen(
                                gameHistory = gameHistory,
                                languageSwitcher = languageSwitcher,
                                back = {
                                    if(navigationController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED)
                                        navigationController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
