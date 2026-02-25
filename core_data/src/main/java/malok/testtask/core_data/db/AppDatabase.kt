package malok.testtask.core_data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}