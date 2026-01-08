package malok.todoreminder.domain

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val createdAt: Long
)
