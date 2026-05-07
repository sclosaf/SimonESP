package unipd.esp2526.Simon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDao
{
    @Insert
    suspend public fun insert(match: MatchEntity)

    @Query("SELECT * FROM Matches ORDER BY id DESC")
    suspend public fun getAllMatches(): List<MatchEntity>

    @Query("DELETE FROM Matches")
    suspend public fun deleteAllMatches()
}
