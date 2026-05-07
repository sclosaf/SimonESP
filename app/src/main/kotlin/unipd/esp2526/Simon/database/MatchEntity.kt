package unipd.esp2526.Simon.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Matches")
data class MatchEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val fullSequence: String,
    val errorIndex: Int?
)
