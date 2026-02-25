package malok.testtask.detail.detail.domain

import kotlinx.coroutines.flow.Flow
import malok.testtask.core.domain.Task

interface DetailRepository {

    suspend fun getTaskById(id: Long): Task?

    fun observeTaskById(id: Long): Flow<Task>

    suspend fun updateStatus(id: Long, check: Boolean)

    suspend fun deleteTask(id: Long)

}