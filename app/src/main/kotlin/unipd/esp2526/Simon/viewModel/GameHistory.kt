package unipd.esp2526.Simon.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.ui.theme.ColorType
import unipd.esp2526.Simon.database.MatchDatabase
import unipd.esp2526.Simon.database.MatchEntity

/**
 * Data class representing a completed match.
 *
 * @property fullSequence The complete sequence of colors played during the match
 * @property errorIndex The zero-based index where the first error occurred,
 *                      or null if the player completed the sequence correctly
 */
data class Match(
    val fullSequence: List<ColorType>,
    val errorIndex: Int?
)

/**
 * ViewModel used to manage the completed matches history.
 * This class is responsible for loading, storing and clearing data, using a database.
 *
 * Operations are performed asynchronously.
 */
class GameHistory : ViewModel()
{
    /**
     * List of the completed matches.
     */
    var endedMatches = mutableStateListOf<Match>()
        private set

    private lateinit var database: MatchDatabase

    /**
     * Initializes the database and loads existing matches.
     *
     * Must be called before any other operation is performed,
     * in order to ensure the database is initialized.
     *
     * @param context The android context used to obtain the database instance
     */
    public fun initDatabase(context: Context)
    {
        if(!::database.isInitialized)
        {
            database = MatchDatabase.getDatabase(context)
            loadAllMatches()
        }
    }

    /**
     * Helper method that loads all the matches from the database into the ViewModel.
     * The operation runs on an IO Dispatcher in order to avoid any delay on the main thread.
     */
    private fun loadAllMatches()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            val matches = database.matchDao().getAllMatches().map { entity ->
                Match(
                    fullSequence = entity.fullSequence.split(",").map { ColorType.valueOf(it)},
                    errorIndex = entity.errorIndex
                )
            }

            withContext(Dispatchers.Main)
            {
                endedMatches.clear()
                endedMatches.addAll(matches)
            }
        }
    }

    /**
     * Adds a completed match to the database and updates the auxiliary list.
     * Empty parameters are ignored and not saved.
     *
     * @param sequence The complete sequence of the match
     * @param errorIndex The index where the first error occurred
     */
    public fun addSequence(sequence : List<ColorType>, errorIndex: Int?)
    {
        if(!::database.isInitialized)
            return

        if(sequence.isEmpty() || errorIndex == null)
            return

        viewModelScope.launch(Dispatchers.IO)
        {
            database.matchDao().insert(
                MatchEntity(
                    fullSequence = sequence.joinToString(",") { it.name },
                    errorIndex = errorIndex
                )
            )

            withContext(Dispatchers.Main){ endedMatches.add(Match(fullSequence = sequence, errorIndex = errorIndex)) }
        }
    }

    /**
     * Deletes all the existing matches both from the database and the stored list.
     * This operation is irreversible and permanently removes the history.
     */
    public fun clearHistory()
    {
        if(!::database.isInitialized)
            return

        viewModelScope.launch(Dispatchers.IO)
        {
            database.matchDao().deleteAllMatches()
            withContext(Dispatchers.Main){ endedMatches.clear() }
        }
    }
}
