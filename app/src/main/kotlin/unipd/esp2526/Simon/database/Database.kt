package unipd.esp2526.Simon.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

/**
 * Database to store the matches played.
 *
 * The database is implemented as a singleton to avoid
 * multiple instances being opened simultaneously.
 * Access to the database instance is thread-safe using synchronized.
 */
@Database(
    entities = [MatchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MatchDatabase : RoomDatabase()
{
    /**
     * Provides access to the MatchDao for database operations.
     *
     * @return An instance of MatchDao
     */
    abstract fun matchDao(): MatchDao

    companion object
    {
        @Volatile
        private var instance: MatchDatabase? = null

        /**
         * Retrieves the singleton instance of MatchDatabase.
         *
         * This method ensures that only one database instance exists throughout
         * the application lifecycle.
         *
         * @param context The context used to create the database
         * @return The singleton MatchDatabase instance
         */
        fun getDatabase(context: Context): MatchDatabase
        {
            return instance ?: synchronized(this)
            {
                val db = Room.databaseBuilder(context.applicationContext, MatchDatabase::class.java, "MatchDatabase").build()
                instance = db
                db
            }
        }
    }
}
