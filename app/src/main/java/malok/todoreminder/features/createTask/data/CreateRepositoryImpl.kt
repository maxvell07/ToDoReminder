package malok.todoreminder.features.createTask.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import malok.testtask.core.domain.Task
import malok.todoreminder.data.db.TaskDao
import malok.todoreminder.data.mappers.toEntity
import malok.todoreminder.features.createTask.domain.CreateRepository

class CreateRepositoryImpl(
    private val dao: TaskDao
): CreateRepository {

    override suspend fun createTask(task: Task) = withContext(Dispatchers.IO)  {
        dao.createTask(task.toEntity())
    }

}