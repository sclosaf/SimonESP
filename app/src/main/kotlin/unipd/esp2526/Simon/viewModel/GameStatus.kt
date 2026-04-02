package unipd.esp2526.Simon.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import unipd.esp2526.Simon.ui.theme.ColorType

class GameStatus : ViewModel()
{
    companion object
    {
        private const val LIGHT_DURATION_MS = 150L
    }

    var currentSequence by mutableStateOf<List<ColorType>>(emptyList())
        private set

    var isGameActive by mutableStateOf(true)
        private set

    var litColor by mutableStateOf<ColorType?>(null)
        private set

    private var currentLightJob: kotlinx.coroutines.Job? = null

    public fun addColor(color: ColorType)
    {
        if(isGameActive)
        {
            currentSequence = currentSequence + color
            illuminateColor(color)
        }
    }

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

    public fun clearSequence()
    {
        currentLightJob?.cancel()
        litColor = null
        currentSequence = emptyList()
    }

    public fun endGame() : List<ColorType>
    {
        currentLightJob?.cancel()
        litColor = null

        val finalSequence = currentSequence
        clearSequence()
        isGameActive = false
        return finalSequence
    }

    public fun reset()
    {
        currentLightJob?.cancel()
        currentSequence = emptyList()
        isGameActive = true
        litColor = null
    }

    override fun onCleared()
    {
        super.onCleared()
        currentLightJob?.cancel()
    }
}
