package malok.testtask.edit_task.editTask.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import malok.testtask.core.domain.Task
import malok.testtask.core_data.db.TaskDao
import malok.testtask.core_data.mappers.toDomain
import malok.testtask.core_data.mappers.toEntity
import malok.testtask.edit_task.editTask.domain.EditRepository

class EditRepositoryImpl(
    private val dao: TaskDao
) : EditRepository {

    override suspend fun getTaskById(taskId: Long): Task =
        withContext(Dispatchers.IO) {
            dao.getTaskById(taskId)?.toDomain() ?: Task()
        }

    override suspend fun updateTask(task: Task) =
        withContext(Dispatchers.IO) {
            dao.updateTask(task.toEntity())
        }
}
