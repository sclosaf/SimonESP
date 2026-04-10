package unipd.esp2526.Simon.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import unipd.esp2526.Simon.ui.theme.ColorType

/**
 * ViewModel used to manage the state of the active game session.
 *
 * It is responsible for tracking the current color sequence,
 * managing the visual feedback of the buttons illumination,
 * resetting or ending the session.
 */
class GameStatus : ViewModel()
{
    /**
     * Duration in milliseconds for which a color remains illuminated.
     *
     * Used to control how long the visual feedback lasts.
     */
    private const val LIGHT_DURATION_MS = 275L

    /**
     * The sequence of color entered during the current session played.
     *
     * Restarts as empty once a new session begins.
     */
    var currentSequence by mutableStateOf<List<ColorType>>(emptyList())
        private set

    /**
     * The color currently illuminated.
     *
     * This effect is played once a button is pressed,
     * it lasts as many milliseconds as LIGHT_DURATION_MS defines.
     */
    var litColor by mutableStateOf<ColorType?>(null)
        private set

    /**
     * Reference to the currently running illumination coroutine job.
     *
     * It manages the mutual exclusion between sequential pressions,
     * preventing overlapping effects.
     */
    private var currentLightJob: kotlinx.coroutines.Job? = null

    /**
     * Adds a color to the current sequence and triggers its illumination.
     *
     * @param color The color to add to the sequence and illuminate
     */
    public fun addColor(color: ColorType)
    {
        currentSequence = currentSequence + color
        illuminateColor(color)
    }

    /**
     * Illuminates a specific color for visual feedback.
     * It cancels any previously running illumination to prevent conflicts.
     *
     * @param color The color to illuminate
     */
    private fun illuminateColor(color: ColorType)
    {
        currentLightJob?.cancel()

        currentLightJob = viewModelScope.launch{
            litColor = color

            delay(LIGHT_DURATION_MS)

            if(litColor == color)
            {
                litColor = null
            }
        }
    }

    /**
     * Clears the current game sequence without returning it.
     * Used when the user decides to cancel a game.
     */
    public fun clearSequence()
    {
        currentLightJob?.cancel()
        litColor = null
        currentSequence = emptyList()
    }

    /**
     * Ends the current game and returns the final sequence.
     * Used when the user confirms the end of a game.
     *
     * @return The final sequence of colors from the ended game
     */
    public fun endGame() : List<ColorType>
    {
        currentLightJob?.cancel()
        litColor = null

        val finalSequence = currentSequence
        clearSequence()
        return finalSequence
    }

    /**
     * Resets the game state to its initial values.
     * Additionally cancels any pending illuminations.
     */
    public fun reset()
    {
        currentLightJob?.cancel()
        currentSequence = emptyList()
        litColor = null
    }

    /**
     * Called when the ViewModel is about to be destroyed.
     * Cancels any pending illumination.
     */
    override fun onCleared()
    {
        super.onCleared()
        currentLightJob?.cancel()
    }
}
