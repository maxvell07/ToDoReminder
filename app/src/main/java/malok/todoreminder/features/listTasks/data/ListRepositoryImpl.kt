package malok.todoreminder.features.listTasks.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import malok.todoreminder.data.db.TaskDao
import malok.todoreminder.data.mappers.toDomain
import malok.todoreminder.features.listTasks.domain.ListRepository
import malok.todoreminder.domain.Task

class ListRepositoryImpl(
    private val dao: TaskDao
) : ListRepository {

    override fun observeTasks(): Flow<List<Task>> =
        dao.observeTasks().map { list ->
            list.map { it.toDomain() }
        }

}