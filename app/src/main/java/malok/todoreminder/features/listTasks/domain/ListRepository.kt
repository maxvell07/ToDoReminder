package malok.todoreminder.features.listTasks.domain

import kotlinx.coroutines.flow.Flow
import malok.todoreminder.domain.Task

interface ListRepository {
    fun observeTasks(): Flow<List<Task>>
}