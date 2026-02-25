package malok.testtask.detail.detail.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import malok.testtask.core.domain.Task
import malok.testtask.core_data.db.TaskDao
import malok.testtask.core_data.mappers.toDomain
import malok.testtask.detail.detail.domain.DetailRepository

class DetailRepositoryImpl(
    private val dao: TaskDao
) : DetailRepository {

    override suspend fun getTaskById(id: Long): Task? = withContext(Dispatchers.IO) {
        dao.getTaskById(id)?.toDomain()
    }

    override fun observeTaskById(id: Long):Flow<Task> = dao.observeTaskById(id).map { it.toDomain() }
        .flowOn(Dispatchers.IO)


    override suspend fun updateStatus(id: Long, check: Boolean) {
        dao.updateTaskDone(id,check)
    }

    override suspend fun deleteTask(id: Long) = withContext(Dispatchers.IO) {
        dao.deleteTaskById(id)
    }

}