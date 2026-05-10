package unipd.esp2526.Simon.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel

import unipd.esp2526.Simon.ui.theme.ColorType
import unipd.esp2526.Simon.database.MatchDatabase
import unipd.esp2526.Simon.database.MatchEntity

data class Match(
    val fullSequence: List<ColorType>,
    val errorIndex: Int?
)

class GameHistory : ViewModel()
{
    var endedMatches = mutableStateListOf<Match>()
        private set

    private lateinit var database: MatchDatabase

    public fun initDatabase(context: android.content.Context)
    {
        if(!::database.isInitialized)
        {
            database = MatchDatabase.getDatabase(context)
            loadAllMatches()
        }
    }

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

    fun clearHistory()
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            database.matchDao().deleteAllMatches()
            withContext(Dispatchers.Main){ endedMatches.clear() }
        }
    }
}
