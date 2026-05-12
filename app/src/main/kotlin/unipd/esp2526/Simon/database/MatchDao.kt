package unipd.esp2526.Simon.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Data Access Object for the MatchEntity table.
 * This interface defines the operations to manage stored matches.
 *
 * Operations provided:
 * - Insert a new completed match into the database
 * - Retrieve all matches ordered by ID descending (newest first)
 * - Delete all matches
 */
@Dao
interface MatchDao
{
    /**
     * Inserts a match into the database.
     *
     * @param match The match entity
     */
    @Insert
    suspend fun insert(match: MatchEntity)

    /**
     * Retrieves all the matches from the table.
     *
     * @return List of all matches ordered by ID in descending order
     */
    @Query("SELECT * FROM Matches ORDER BY id DESC")
    suspend fun getAllMatches(): List<MatchEntity>

    /**
     * Deletes all the matches stored.
     * This operation is irreversible.
     */
    @Query("DELETE FROM Matches")
    suspend fun deleteAllMatches()
}
