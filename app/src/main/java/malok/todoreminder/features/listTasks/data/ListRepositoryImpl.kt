package malok.todoreminder.features.listTasks.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import malok.todoreminder.data.db.TaskDao
import malok.todoreminder.data.mappers.toDomain
import malok.todoreminder.features.listTasks.domain.ListRepository
import malok.todoreminder.domain.Task

class ListRepositoryImpl(
    private val dao: TaskDao
) : ListRepository {

    override fun observeTasks(): Flow<List<Task>> =
        dao.observeTasks().catch { emit(emptyList()) }.map { list ->
            list.map { it.toDomain() }
        }.flowOn(Dispatchers.Default)

    override suspend fun updateStatus(id: Long, check: Boolean) =
        withContext(Dispatchers.IO) {
            dao.updateTaskDone(id, check)
        }
}