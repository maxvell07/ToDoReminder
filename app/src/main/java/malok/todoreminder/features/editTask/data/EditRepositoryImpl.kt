package malok.todoreminder.features.editTask.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import malok.todoreminder.data.db.TaskDao
import malok.todoreminder.data.mappers.toDomain
import malok.todoreminder.data.mappers.toEntity
import malok.todoreminder.domain.Task
import malok.todoreminder.features.editTask.domain.EditRepository

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
