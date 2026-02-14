package malok.todoreminder.features.listTasks.domain

import kotlinx.coroutines.flow.Flow
import malok.testtask.core.domain.Task

interface ListRepository {
    fun observeTasks(): Flow<List<Task>>

    suspend fun updateStatus(id: Long, check: Boolean)
}