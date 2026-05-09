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

enum class GamePhase
{
    IDLE,
    COMPUTER,
    PLAYER,
    CONTINUE,
    OVER
}

class GameStatus(private val audioPlayer: AudioPlayer) : ViewModel()
{
    companion object
    {
        private const val LIGHT_DURATION_MS = 800L
        private const val DELAY_BETWEEN_COLORS_DURATION_MS = 500L
        private const val DELAY_PAUSED_GAME_DURATION_MS = 150L

        private const val KEY_CURRENT_PHASE = "currentPhase"
        private const val KEY_TARGET_SEQUENCE = "targetSequence"
        private const val KEY_PLAYED_SEQUENCE = "playedSequence"
        private const val KEY_IS_PAUSED = "isPaused"
        private const val KEY_ERROR_INDEX = "errorIndex"
        private const val KEY_CURRENT_INDEX = "computerIndex"
    }

    var currentPhase by mutableStateOf(GamePhase.IDLE)
        private set

    var targetSequence by mutableStateOf<List<ColorType>>(emptyList())
        private set

    var playedSequence by mutableStateOf<List<ColorType>>(emptyList())
        private set

    var litColor by mutableStateOf<ColorType?>(null)
        private set

    var isPaused by mutableStateOf(false)
        private set

    var errorIndex by mutableStateOf<Int?>(null)
        private set

    var computerIndex by mutableStateOf(0)
        private set

    private var toResume = false

    private var currentLightJob: Job? = null

    private fun illuminateColor(color: ColorType)
    {
        currentLightJob?.cancel()

        currentLightJob = viewModelScope.launch {

            litColor = color
            audioPlayer.play(color)
            delay(LIGHT_DURATION_MS)
            if(litColor == color)
                litColor = null
        }
    }

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

    public fun startGame()
    {
        resetGame()
        currentPhase = GamePhase.COMPUTER
        nextRound()
    }

    private fun nextRound()
    {
        if(currentPhase != GamePhase.COMPUTER)
            return

        playedSequence = emptyList()
        targetSequence += ColorType.values().random()
        computerIndex = 0

        illuminateSequence()
    }

    public fun continueNextRound()
    {
        if(currentPhase != GamePhase.CONTINUE)
            return

        currentPhase = GamePhase.COMPUTER
        nextRound()
    }

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

    public fun togglePause()
    {
        if(currentPhase == GamePhase.COMPUTER)
            isPaused = !isPaused
    }

    private fun endGame() : Pair<List<ColorType>, Int?>?
    {
        if(currentPhase == GamePhase.OVER)
        {
            return if(targetSequence.isNotEmpty()) Pair(targetSequence, errorIndex) else null
        }

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

    public fun forceEndGame() : Pair<List<ColorType>, Int?>?
    {
        if(targetSequence.size == 1 && playedSequence.isEmpty() && errorIndex == null)
        {
            resetGame()
            return null
        }

        if(errorIndex == null && targetSequence.isNotEmpty())
        {
            if(currentPhase == GamePhase.CONTINUE && playedSequence.isEmpty())
                errorIndex = targetSequence.size
            else
                errorIndex = playedSequence.size
        }

        return endGame()
    }

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
    }

    public fun restoreState(bundle: Bundle)
    {
        toResume = true

        currentPhase = GamePhase.valueOf(bundle.getString(KEY_CURRENT_PHASE, GamePhase.IDLE.name))

        targetSequence = bundle.getStringArrayList(KEY_TARGET_SEQUENCE)?.mapNotNull { name -> ColorType.valueOf(name) } ?: emptyList()
        playedSequence = bundle.getStringArrayList(KEY_PLAYED_SEQUENCE)?.mapNotNull { name -> ColorType.valueOf(name) } ?: emptyList()

        isPaused = bundle.getBoolean(KEY_IS_PAUSED)

        errorIndex = if(bundle.containsKey(KEY_ERROR_INDEX)) bundle.getInt(KEY_ERROR_INDEX) else null
        computerIndex = bundle.getInt(KEY_CURRENT_INDEX)
    }

    public fun resumeIfNeeded()
    {
        if(!toResume)
            return

        toResume = false

        if(currentPhase == GamePhase.COMPUTER)
            illuminateSequence()
    }

    override fun onCleared()
    {
        super.onCleared()
        currentLightJob?.cancel()
    }
}
