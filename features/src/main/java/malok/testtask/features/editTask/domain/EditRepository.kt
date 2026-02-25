package malok.testtask.features.editTask.domain

import malok.testtask.core.domain.Task


interface EditRepository {
    suspend fun getTaskById(taskId: Long): Task
    suspend fun updateTask(task: Task)
}