package unipd.esp2526.Simon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity table representing a completed match.
 *
 * Each instance stores the complete color sequence of a match
 * and the position where the first mistake occurred.
 *
 * @property id Auto-generated primary key uniquely identifying each match
 * @property fullSequence The complete sequence of colors as a string
 * @property errorIndex The zero-based index where the first error occurred
 */
@Entity(tableName = "Matches")
data class MatchEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val fullSequence: String,
    val errorIndex: Int?
)
