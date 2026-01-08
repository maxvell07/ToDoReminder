package malok.todoreminder.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val createdAt: Long
)