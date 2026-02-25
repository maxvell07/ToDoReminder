package malok.testtask.features.createTask.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import malok.testtask.core.domain.Task
import malok.testtask.core_data.db.TaskDao
import malok.testtask.core_data.mappers.toEntity
import malok.testtask.features.createTask.domain.CreateRepository

class CreateRepositoryImpl(
    private val dao: TaskDao
): CreateRepository {

    override suspend fun createTask(task: Task) = withContext(Dispatchers.IO)  {
        dao.createTask(task.toEntity())
    }

}