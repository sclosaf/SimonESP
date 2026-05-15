package unipd.esp2526.Simon.viewModel

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import unipd.esp2526.Simon.ui.theme.ColorType

/**
 * Enumeration representing the possible states of the game:
 * - IDLE: No game in progress, waiting to start one
 * - COMPUTER: Computer is playing a sequence
 * - PLAYER: Player's turn to repeat the shown sequence
 * - CONTINUE: Player repeated the sequence correctly, waiting to continue or end the match
 * - OVER: Game has ended (by mistake or player's choice)
 */
enum class GamePhase
{
    IDLE,
    COMPUTER,
    PLAYER,
    CONTINUE,
    OVER
}

/**
 * ViewModel that manages the core game logic and state.
 *
 * It handles:
 * - The computer sequence generation and playback with both audio and visual feedback
 * - Validates player's input
 * - Game phase transition (IDLE -> COMPUTER -> PLAYER -> CONTINUE -> ...)
 * - Allows stopping the game during computer's turn
 *
 * @param audioPlayer Used to play sound effects
 */
class GameStatus(private val audioPlayer: AudioPlayer) : ViewModel()
{
    companion object
    {
        // Milliseconds of delay to use during each stage of the visual feedback.
        private const val LIGHT_DURATION_MS = 800L
        private const val DELAY_BETWEEN_COLORS_DURATION_MS = 500L
        private const val DELAY_PAUSED_GAME_DURATION_MS = 150L

        // State persistence keys for the core class members.
        private const val KEY_CURRENT_PHASE = "currentPhase"
        private const val KEY_TARGET_SEQUENCE = "targetSequence"
        private const val KEY_PLAYED_SEQUENCE = "playedSequence"
        private const val KEY_IS_PAUSED = "isPaused"
        private const val KEY_ERROR_INDEX = "errorIndex"
        private const val KEY_CURRENT_INDEX = "computerIndex"
        private const val KEY_ALLOW_RESTORE = "allowRestore"
    }

    /**
     * Stores the current phase of the game.
     */
    var currentPhase by mutableStateOf(GamePhase.IDLE)
        private set

    /**
     * Stores the sequence the player must reproduce in the current turn.
     */
    var targetSequence by mutableStateOf<List<ColorType>>(emptyList())
        private set

    /**
     * Stores the sequence of colors pressed by the player in the current turn.
     */
    var playedSequence by mutableStateOf<List<ColorType>>(emptyList())
        private set

    /**
     * Stores the currently illuminated color, null if none is lit.
     */
    var litColor by mutableStateOf<ColorType?>(null)
        private set

    /**
     * Stores whether the computer playback is currently paused.
     */
    var isPaused by mutableStateOf(false)
        private set

    /**
     * Stores the index 0-based where the first error occurred, null if none yet.
     */
    var errorIndex by mutableStateOf<Int?>(null)
        private set

    /**
     * Stores the current position during computer playback (keeps track where to resume the sequence).
     */
    var computerIndex by mutableStateOf(0)
        private set

    /**
     * Stores whether the state restoration is allowed (set to false when the activity is finishing).
     */
    var allowRestore = true
        private set

    private var hasToResume = false
    private var currentLightJob: Job? = null

    /**
     * Helper method that temporarily illuminates a single color and plays its sound.
     *
     * @param color The color to illuminate
     */
    private fun illuminateColor(color: ColorType)
    {
        currentLightJob?.cancel()

        currentLightJob = viewModelScope.launch {
            audioPlayer.play(color)

            litColor = color
            delay(LIGHT_DURATION_MS)

            if(litColor == color)
                litColor = null
        }
    }

    /**
     * Helper method that plays the current target, starting from computerIndex.
     * Manages both visual and audio feedback for each color of the sequence.
     *
     * Transitions to the player phase once concluded.
     */
    private fun illuminateSequence()
    {
        currentLightJob?.cancel()

        currentLightJob = viewModelScope.launch{
            for(index in computerIndex until targetSequence.size)
            {
                while(isPaused)
                    delay(DELAY_PAUSED_GAME_DURATION_MS)

                computerIndex = index
                val color = targetSequence[index]

                audioPlayer.play(color)

                litColor = color
                delay(LIGHT_DURATION_MS)

                if(litColor == color)
                    litColor = null

                delay(DELAY_BETWEEN_COLORS_DURATION_MS)
            }

            if(currentPhase == GamePhase.COMPUTER)
            {
                while(isPaused)
                    delay(DELAY_PAUSED_GAME_DURATION_MS)

                currentPhase = GamePhase.PLAYER
                playedSequence = emptyList()
                computerIndex = 0
            }
        }
    }

    /**
     * Starts a new game, resets the game
     * state to default and begins the first round.
     */
    public fun startGame()
    {
        resetGame()
        currentPhase = GamePhase.COMPUTER
        nextRound()
    }

    /**
     * Helper method that appends a new random color
     * to the target sequence and start the computer's turn.
     */
    private fun nextRound()
    {
        if(currentPhase != GamePhase.COMPUTER)
            return

        playedSequence = emptyList()
        targetSequence += ColorType.values().random()
        computerIndex = 0

        illuminateSequence()
    }

    /**
     * Continues to the next round after a successful player's turn.
     */
    public fun continueNextRound()
    {
        if(currentPhase != GamePhase.CONTINUE)
            return

        currentPhase = GamePhase.COMPUTER
        nextRound()
    }

    /**
     * Handles a player's press during its turn.
     *
     * @param color The color pressed by the player
     * @return Pair(fullSequence, errorIndex) if the game ends, null otherwise
     */
    public fun colorPressed(color: ColorType) : Pair<List<ColorType>, Int?>?
    {
        if(currentPhase != GamePhase.PLAYER)
            return null

        illuminateColor(color)

        playedSequence += color

        if(color != targetSequence[playedSequence.size - 1])
        {
            errorIndex = playedSequence.size - 1
            return endGame()
        }

        if(playedSequence.size == targetSequence.size)
            currentPhase = GamePhase.CONTINUE

        return null
    }

    /**
     * Toggles the pause state during computer playback.
     */
    public fun togglePause()
    {
        if(currentPhase == GamePhase.COMPUTER)
            isPaused = !isPaused
    }

    /**
     * Helper function that ends the game and return the result of the match.
     *
     * @return Pair(fullSequence, errorIndex) or null if no match was played
     */
    private fun endGame() : Pair<List<ColorType>, Int?>?
    {
        if(currentPhase == GamePhase.OVER)
            return Pair(targetSequence, errorIndex)

        if(currentPhase == GamePhase.IDLE)
            return null

        if(currentPhase != GamePhase.IDLE && targetSequence.isNotEmpty())
        {
            currentPhase = GamePhase.OVER
            return Pair(targetSequence, errorIndex)
        }

        currentPhase = GamePhase.OVER
        return null
    }

    /**
     * Forces the game to end (by the player's choice)
     *
     * @return Pair(fullSequence, errorIndex) or null if no match was played
     */
    public fun forceEndGame() : Pair<List<ColorType>, Int?>?
    {
        if(targetSequence.size == 1 && playedSequence.isEmpty())
        {
            if(currentPhase == GamePhase.COMPUTER)
            {
                resetGame()
                return null
            }
        }

        if(errorIndex == null)
        {
            if(currentPhase == GamePhase.CONTINUE && playedSequence.isEmpty())
                errorIndex = targetSequence.size
            else
                errorIndex = playedSequence.size
        }

        return endGame()
    }

    /**
     * Resets the game state to IDLE, clears all sequences and cancels any ongoing playbacks.
     */
    public fun resetGame()
    {
        targetSequence = emptyList()
        playedSequence = emptyList()

        currentPhase = GamePhase.IDLE

        currentLightJob?.cancel()
        litColor = null

        isPaused = false
        errorIndex = null
        computerIndex = 0
    }

    /**
     * Saves the current game state into the given Bundle.
     * Used to preserve the state across configuration changes.
     *
     * @param bundle The Bundle to save the state into
     */
    public fun saveState(bundle: Bundle)
    {
        currentLightJob?.cancel()

        bundle.putString(KEY_CURRENT_PHASE, currentPhase.name)
        bundle.putStringArrayList(KEY_TARGET_SEQUENCE, ArrayList(targetSequence.map { it.name }))
        bundle.putStringArrayList(KEY_PLAYED_SEQUENCE, ArrayList(playedSequence.map { it.name }))
        bundle.putBoolean(KEY_IS_PAUSED, isPaused)

        if(errorIndex != null)
            bundle.putInt(KEY_ERROR_INDEX, errorIndex!!)

        bundle.putInt(KEY_CURRENT_INDEX, computerIndex)

        bundle.putBoolean(KEY_ALLOW_RESTORE, allowRestore)
    }

    /**
     * Restores the game state from the given Bundle.
     *
     * @param bundle The Bundle containing the saved state
     */
    public fun restoreState(bundle: Bundle)
    {
        hasToResume = true

        currentPhase = GamePhase.valueOf(bundle.getString(KEY_CURRENT_PHASE, GamePhase.IDLE.name))

        targetSequence = bundle.getStringArrayList(KEY_TARGET_SEQUENCE)?.mapNotNull { name -> ColorType.valueOf(name) } ?: emptyList()
        playedSequence = bundle.getStringArrayList(KEY_PLAYED_SEQUENCE)?.mapNotNull { name -> ColorType.valueOf(name) } ?: emptyList()

        isPaused = bundle.getBoolean(KEY_IS_PAUSED)

        errorIndex = if(bundle.containsKey(KEY_ERROR_INDEX)) bundle.getInt(KEY_ERROR_INDEX) else null
        computerIndex = bundle.getInt(KEY_CURRENT_INDEX) + 1

        allowRestore = bundle.getBoolean(KEY_ALLOW_RESTORE)
    }

    /**
     * Resumes a paused computer sequence
     * after restoring the state.
     */
    public fun resumeIfNeeded()
    {
        if(!hasToResume)
            return

        hasToResume = false

        if(currentPhase == GamePhase.COMPUTER)
            illuminateSequence()
    }

    /**
     * Disables future state restorations.
     */
    public fun disableRestore()
    {
        allowRestore = false
    }

    /**
     * Checks whether a saved Bundle can be restored.
     *
     * @param bundle The Bundle to check
     * @return true if restoration is allowed
     */
    public fun canRestore(bundle: Bundle): Boolean
    {
        return bundle.getBoolean(KEY_ALLOW_RESTORE, true)
    }

    /**
     * Override onClear method.
     */
    override fun onCleared()
    {
        super.onCleared()
        currentLightJob?.cancel()
    }
}
