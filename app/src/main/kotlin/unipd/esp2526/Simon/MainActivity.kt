package unipd.esp2526.Simon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.activity.viewModels
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
import unipd.esp2526.Simon.viewModel.AudioPlayer

/**
 * Main activity of the Simon application.
 *
 * This activity serves as entry point for the app, managing:
 * - ViewModels initialization for language, audio and match history
 * - Game state persistence across configuration changes and activity lifecycle
 * - Navigation between the three screens (Home, Game and Detail)
 * - Audio management (pause and resume) when the activity loses foreground
 *
 * Enables edge-to-edge display and applies a custom theme
 * with dark/light mode support.
 *
 * Allowed navigation flow:
 * - HomeScreen -> GameScreen (starting a new game)
 * - HomeScreen -> DetailScreen (view a specific match details)
 * - GameScreen -> HomeScreen (on game ended, return to home)
 * - DetailScreen -> HomeScreen (return to home)
 *
 * The game state is saved and restored to preserve a match during any configuration change
 * with the exception when the activity is being closed by the user.
 */
class MainActivity : AppCompatActivity()
{
    companion object
    {
        private const val KEY_GAME_STATUS = "gameStatus"
    }

    private val languageSwitcher: LanguageSwitcher by viewModels()
    private val audioPlayer: AudioPlayer by viewModels()
    private val gameHistory: GameHistory by viewModels()

    private lateinit var gameStatus: GameStatus

    /**
     * Override onCreate method.
     *
     * Initializes the environment,
     * required objects, and sets up navigation.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        gameStatus = GameStatus(audioPlayer)

        audioPlayer.loadSounds(this)
        gameHistory.initDatabase(this)
        savedInstanceState?.getBundle(KEY_GAME_STATUS)?.let { bundle -> if(gameStatus.canRestore(bundle)) gameStatus.restoreState(bundle) }

        enableEdgeToEdge()

        setContent {
            val navigationController = rememberNavController()

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
                                },
                                onClearHistory = { gameHistory.clearHistory() }

                            )
                        }

                        composable("DetailScreen/{index}") { entry ->
                            val index = entry.arguments?.getString("index")?.toIntOrNull() ?: -1

                            val match = if(index in gameHistory.endedMatches.indices)
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
                                gameStatus = gameStatus,
                                audioPlayer = audioPlayer
                            )
                        }
                    }
                }
            }
        }
    }

    /**
     * Override onSaveInstanceState method.
     *
     * Saves the game state before the activity is suspended.
     *
     * Only GameStatus requires explicit state saving because it
     * holds the active game session (current sequence, game phase, etc.).
     * Other ViewModels either manage their own persistence
     * or do not need to survive configuration changes.
     */
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

    /**
     * Override onStop method.
     *
     * Called when the activity is no longer visible.
     *
     * Disables game state restoration only when the activity is being
     * permanently closed (user finishes the app).
     * This prevents restoring a stale game state on a fresh launch.
     */
    override fun onStop()
    {
        super.onStop()
        if(isFinishing())
            gameStatus.disableRestore()
    }

    /**
     * Override onPause method.
     */
    override fun onPause()
    {
        super.onPause()
        audioPlayer.pause()
    }

    /**
     * Override onResume method.
     */
    override fun onResume()
    {
        super.onResume()
        audioPlayer.resume()
    }
}
