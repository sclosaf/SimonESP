package unipd.esp2526.Simon.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.ui.theme.ColorType

data class Match(
    val fullSequence: List<ColorType>,
    val errorIndex: Int?
)

class GameHistory : ViewModel()
{
    var endedMatches = mutableStateListOf<Match>()
        private set

    public fun addSequence(sequence : List<ColorType>, error: Int?)
    {
        endedMatches.add(
            Match(fullSequence = sequence, errorIndex = error)
        )
    }

    fun clearHistory()
    {
        endedMatches.clear()
    }
}
