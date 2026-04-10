package unipd.esp2526.Simon.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.ui.theme.ColorType

/**
 * Data structure to represent a completed match,
 * it stores the essential information of the sesson.
 *
 * @property sequence String representation of the color sequence played in a match,
 *                    each entry is separated by a comma (e.g., "R, G, B, B, Y, M").
 * @property size Integer number of elements in the sequence.
 */
data class Match(
    val sequence : String,
    val size: Int
)

/**
 * ViewModel used to manage the complete matches history of the game.
 *
 * Played matches are stored in a mutable state list to trigger a proper
 * update of the interface when a game is added.
 *
 * @property endedMatches Read-only list of completed matches.
 */
class GameHistory : ViewModel()
{
    /**
     * The list of all completed matches in the current session.
     */
    var endedMatches = mutableStateListOf<Match>()
        private set

    /**
     * Adds a confirmed sequence to the history.
     * Automatically converts the list to a String format,
     * calculating the size of the match.
     *
     * @param colorSequence The sequence of colors played in the game, in order
     */
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
