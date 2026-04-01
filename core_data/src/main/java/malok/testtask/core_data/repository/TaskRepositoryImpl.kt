package malok.testtask.core_data.repository

import malok.testtask.core.domain.Task
import malok.testtask.core.domain.repositories.TaskRepository
import malok.testtask.core_data.db.TaskDao
import malok.testtask.core_data.mappers.toDomain

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun getTasksWithDueTimeAfter(timeMillis: Long): List<Task> {
        val entities = dao.getTasksWithDueTimeAfter(timeMillis)

        return entities.map { it.toDomain() }
    }
}
