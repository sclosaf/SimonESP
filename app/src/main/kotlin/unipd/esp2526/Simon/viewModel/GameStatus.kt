package unipd.esp2526.Simon.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class GameStatus : ViewModel()
{
    companion object
    {
        private const val LIGHT_DURATION_MS = 800L
        private const val DELAY_BETWEEN_COLORS_DURATION_MS = 500L
        private const val DELAY_PAUSED_GAME_DURATION_MS = 150L
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

    private var playerLightJob: kotlinx.coroutines.Job? = null
    private var computerLightJob: kotlinx.coroutines.Job? = null

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

        targetSequence += ColorType.values().random()

        illuminateComputerSequence()
    }

    private fun illuminateComputerSequence()
    {
        computerLightJob?.cancel()
        computerLightJob = viewModelScope.launch{

            for((index, color) in targetSequence.withIndex())
            {
                while(isPaused)
                    delay(DELAY_PAUSED_GAME_DURATION_MS)

                illuminateColor(color)
                delay(LIGHT_DURATION_MS)
                turnOffColor(color)
                delay(DELAY_BETWEEN_COLORS_DURATION_MS)
            }

            if(!isPaused && currentPhase == GamePhase.COMPUTER)
            {
                currentPhase = GamePhase.PLAYER
                playedSequence = emptyList()
            }
        }

    }

    public fun colorPressed(color: ColorType) : Pair<List<ColorType>, Int?>?
    {
        if(currentPhase != GamePhase.PLAYER)
            return null

        playerLightJob?.cancel()
        playerLightJob = viewModelScope.launch{

            illuminateColor(color)
            delay(LIGHT_DURATION_MS)
            turnOffColor(color)
        }

        playedSequence += color

        if(color != targetSequence[playedSequence.size - 1])
        {
            errorIndex = playedSequence.size - 1
            return endGame()
        }

        if(playedSequence.size == targetSequence.size)
        {
            currentPhase = GamePhase.CONTINUE
            playedSequence = emptyList()
        }

        return null
    }

    public fun continueToNextRound()
    {
        if(currentPhase != GamePhase.CONTINUE)
            return

        currentPhase = GamePhase.COMPUTER
        nextRound()
    }

    private fun illuminateColor(color: ColorType)
    {
        litColor = color
    }

    private fun turnOffColor(color: ColorType)
    {
        if(litColor == color)
            litColor = null
    }

    public fun togglePause()
    {
        if(currentPhase == GamePhase.COMPUTER)
            isPaused = !isPaused
    }

    private fun endGame() : Pair<List<ColorType>, Int?>?
    {
        if(currentPhase == GamePhase.OVER || currentPhase == GamePhase.IDLE)
            return null

        playerLightJob?.cancel()
        computerLightJob?.cancel()
        litColor = null

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
        playerLightJob?.cancel()
        computerLightJob?.cancel()
        litColor = null

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
        playerLightJob?.cancel()
        computerLightJob?.cancel()

        targetSequence = emptyList()
        playedSequence = emptyList()

        currentPhase = GamePhase.IDLE

        litColor = null
        isPaused = false
        errorIndex = null
    }

    override fun onCleared()
    {
        super.onCleared()
        playerLightJob?.cancel()
        computerLightJob?.cancel()
    }
}
