package malok.testtask.list_tasks.listTasks.domain

import kotlinx.coroutines.flow.Flow
import malok.testtask.core.domain.Task

interface ListRepository {
    fun observeTasks(): Flow<List<Task>>

    suspend fun updateStatus(id: Long, check: Boolean)
}