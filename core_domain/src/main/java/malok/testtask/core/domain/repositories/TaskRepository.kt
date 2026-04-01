package malok.testtask.core.domain.repositories

import malok.testtask.core.domain.Task

interface TaskRepository {
    suspend fun getTasksWithDueTimeAfter(timeMillis: Long): List<Task>
}