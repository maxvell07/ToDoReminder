package malok.testtask.core.domain

interface TaskRepository {
    suspend fun getTasksWithDueTimeAfter(timeMillis: Long): List<Task>
}