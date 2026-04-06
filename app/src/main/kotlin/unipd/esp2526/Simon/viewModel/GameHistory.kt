package unipd.esp2526.Simon.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.ui.theme.ColorType

data class Match(
    val sequence : String,
    val size: Int
)

class GameHistory : ViewModel()
{
    var endedMatches = mutableStateListOf<Match>()
        private set

    public fun addSequence(colorSequence : List<ColorType>)
    {
        val sequence = colorSequence.joinToString(", ") { it.shortName }
        val size = colorSequence.size
        endedMatches.add(
            Match(
                sequence = sequence,
                size = size
            )
        )
    }
}
