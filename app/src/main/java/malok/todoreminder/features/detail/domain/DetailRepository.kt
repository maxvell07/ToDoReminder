package malok.todoreminder.features.detail.domain

import malok.todoreminder.domain.Task

interface DetailRepository {
    suspend fun getTaskById(id: Long): Task?
}