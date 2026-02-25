package malok.testtask.list_tasks.listTasks.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import malok.testtask.core.domain.Task
import malok.testtask.core_data.db.TaskDao
import malok.testtask.core_data.mappers.toDomain
import malok.testtask.list_tasks.listTasks.domain.ListRepository
import kotlin.collections.map

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