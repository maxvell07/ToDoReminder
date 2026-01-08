package malok.todoreminder.features.detail.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import malok.todoreminder.data.db.TaskDao
import malok.todoreminder.data.mappers.toDomain
import malok.todoreminder.domain.Task
import malok.todoreminder.features.detail.domain.DetailRepository

class DetailRepositoryImpl(
    private val dao: TaskDao
) : DetailRepository {

    override suspend fun getTaskById(id: Long): Task? = withContext(Dispatchers.IO) {
        dao.getTaskById(id)?.toDomain()
    }

}