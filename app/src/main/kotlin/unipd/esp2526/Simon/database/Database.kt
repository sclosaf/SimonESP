package unipd.esp2526.Simon.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(
    entities = [MatchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MatchDatabase : RoomDatabase()
{
    abstract fun matchDao(): MatchDao

    companion object
    {
        @Volatile
        private var instance: MatchDatabase? = null

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
